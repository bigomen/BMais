package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestColaboradorPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public class ColaboradorRepositoryCustomImpl extends Repository<Colaborador> implements ColaboradorRepositoryCustom{

    private final EmpresaRepository empresaRepository;
    private final ClienteRepository clienteRepository;
    private final DependenteRepository dependenteRepository;
    private final DocumentoRepository documentoRepository;
    private final CipaRepository cipaRepository;
    private final DadosBancariosRepository dadosBancariosRepository;
    private final EvolucaoColaboradorRepository evolucaoColaboradorRepository;
    private final FeriasRepository feriasRepository;
    private final AfastamentoRepository afastamentoRepository;
    private final ValeTransporteRepository valeTransporteRepository;
    private final ColaboradorBeneficioRepository colaboradorBeneficioRepository;

    public ColaboradorRepositoryCustomImpl(EmpresaRepository empresaRepository, ClienteRepository clienteRepository, DependenteRepository dependenteRepository, DocumentoRepository documentoRepository, CipaRepository cipaRepository, DadosBancariosRepository dadosBancariosRepository, EvolucaoColaboradorRepository evolucaoColaboradorRepository, FeriasRepository feriasRepository, AfastamentoRepository afastamentoRepository, ValeTransporteRepository valeTransporteRepository, ColaboradorBeneficioRepository colaboradorBeneficioRepository) {
        this.empresaRepository = empresaRepository;
        this.clienteRepository = clienteRepository;
        this.dependenteRepository = dependenteRepository;
        this.documentoRepository = documentoRepository;
        this.cipaRepository = cipaRepository;
        this.dadosBancariosRepository = dadosBancariosRepository;
        this.evolucaoColaboradorRepository = evolucaoColaboradorRepository;
        this.feriasRepository = feriasRepository;
        this.afastamentoRepository = afastamentoRepository;
        this.valeTransporteRepository = valeTransporteRepository;
        this.colaboradorBeneficioRepository = colaboradorBeneficioRepository;
    }

    @Override
    public Collection<Colaborador> lista(RestColaboradorPesquisa params){
        CriteriaQuery<Colaborador> criteria = super.getCriteria();
        Root<Colaborador> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Join<Colaborador, Empresa> joinEmpresa = root.join(Colaborador_.EMPRESA);
        Join<Colaborador, Cargo> joinCargo = root.join(Colaborador_.CARGO);

        Path<Long> idColaborador = root.get(Colaborador_.ID);
        Path<String> nomeColaborador = root.get(Colaborador_.NOME);
        Path<Sexo> sexo = root.get(Colaborador_.SEXO);
        Path<Long> idEmpresa = joinEmpresa.get(Empresa_.ID);
        Path<String> razaoEmpresa = joinEmpresa.get(Empresa_.RAZAO_SOCIAL);
        Path<Long> idCargo = joinCargo.get(Cargo_.ID);
        Path<String> descricaoCargo = joinCargo.get(Cargo_.DESCRICAO);
        Path<StatusColaborador> status = root.get(Colaborador_.STATUS);
        Path<LocalDate> dataAdmissao = root.get(Colaborador_.DATA_ADMISSAO);
        Path<LocalDate> dataNascimento = root.get(Colaborador_.DATA_NASCIMENTO);

        criteria.multiselect(idColaborador, nomeColaborador, sexo, dataNascimento, dataAdmissao, idEmpresa, razaoEmpresa, status, idCargo, descricaoCargo);

        if(StringUtils.isNotBlank(params.getNome())){
            Predicate equal = builder().like(builder().upper(nomeColaborador), like(params.getNome().toUpperCase()));
            conjunction = builder().and(conjunction, equal);
        }
        
        if(params.getSexo() != null){
            Predicate equal = builder().equal(root.get(Colaborador_.SEXO), params.getSexo());
            conjunction = builder().and(conjunction, equal);
        }

        if(StringUtils.isNotBlank(params.getStatus())){
            Join<Colaborador, StatusColaborador> joinStatus = root.join(Colaborador_.STATUS);
            Predicate equal = builder().equal(joinStatus.get(StatusColaborador_.ID), params.getStatus());
            conjunction = builder().and(conjunction, equal);
        }
        
        if(params.getIdade() != null)
        {
        	Expression<Integer> anoAtual = builder().function("DATE_PART", Integer.class, builder().literal("year"), builder().currentDate());
        	Expression<Integer> anoNascimento = builder().function("DATE_PART", Integer.class, builder().literal("year"), root.get(Colaborador_.DATA_NASCIMENTO));
        	Predicate equalIdade = builder().equal(builder().diff(anoAtual, anoNascimento), params.getIdade());
        	conjunction = builder().and(conjunction, equalIdade);
        }
        
        if(params.getInicioContratacao() != null && params.getFimContratacao() != null)
        {
        	Predicate equalDtAdmissao = builder().between(root.get(Colaborador_.DATA_ADMISSAO), params.getInicioContratacao(), params.getFimContratacao());
        	conjunction = builder().and(conjunction, equalDtAdmissao);
        }
        
        if(StringUtils.isNotBlank(params.getCargo()))
        {
        	Predicate equalCargo = builder().equal(root.get(Colaborador_.CARGO).get(Cargo_.ID), UtilSecurity.decryptId(params.getCargo()));
        	conjunction = builder().and(conjunction, equalCargo);
        }

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(nomeColaborador));
        TypedQuery<Colaborador> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();

    }

    @Override
    public Colaborador detalhes(Long id){
        CriteriaQuery<Colaborador> criteria = super.getCriteria();
        Root<Colaborador> root = criteria.from(getClazz());
        root.fetch(Colaborador_.DADOS_BANCARIOS, JoinType.LEFT);
        root.fetch(Colaborador_.MOTIVO_DEMISSAO, JoinType.LEFT);
        criteria.where(builder().equal(root.get(Colaborador_.ID), id));

        TypedQuery<Colaborador> typedQuery = entityManager.createQuery(criteria);
        Colaborador colaborador = typedQuery.getSingleResult();

        colaborador.setCliente(clienteRepository.clienteColaborador(colaborador.getCliente()));
        colaborador.setEmpresa(empresaRepository.empresaDoColaborador(colaborador.getEmpresa()));
        colaborador.setDependentes(dependenteRepository.dependentesColaborador(colaborador.getId()));
        if(colaborador.getDependentes() != null){
            colaborador.getDependentes().forEach(d -> {
                d.setColaboradorBeneficios(colaboradorBeneficioRepository.beneficiosDependentes(d.getId()));
            });
        }
        colaborador.setDocumentos(documentoRepository.documentoColaborador(colaborador.getId()));
        colaborador.setCipas(cipaRepository.cipasColaborador(colaborador.getId()));
        colaborador.setEvolucoes(evolucaoColaboradorRepository.evolucoesColaborador(colaborador.getId()));
        colaborador.setFerias(feriasRepository.feriasColab(colaborador.getId()));
        colaborador.setAfastamentos(afastamentoRepository.afastamentosColaborador(colaborador.getId()));
        if(colaborador.getAfastamentos() != null){
            colaborador.getAfastamentos().forEach(a -> a.setDocumentos(documentoRepository.documentoAfastamento(a.getId())));
        }
        colaborador.setValesTransporte(valeTransporteRepository.valesColaborador(colaborador.getId()));
        colaborador.setColaboradorBeneficios(colaboradorBeneficioRepository.beneficiosColaborador(colaborador.getId()));
        return colaborador;
    }

    @Override
    public Collection<Colaborador> pesquisarColaboradoresPorEmpresa(Empresa empresa)
    {
        CriteriaQuery<Colaborador> criteria = super.getCriteria();
        Root<Colaborador> root = criteria.from(getClazz());
        Join<Colaborador, Cliente> joinCliente = root.join(Colaborador_.CLIENTE, JoinType.LEFT);

        Path<Long> pathId = root.get(Colaborador_.ID);
        Path<String> pathNome = root.get(Colaborador_.NOME);
        Path<BigDecimal> pathSalario = root.get(Colaborador_.SALARIO_BRUTO);
        Path<Long> pathIdCliente = joinCliente.get(Cliente_.ID);
        Path<String> pathRazaoSocial = joinCliente.get(Cliente_.RAZAO_SOCIAL);

        criteria.multiselect(pathId, pathNome, pathIdCliente, pathRazaoSocial, pathSalario);
        Predicate equalEmpresa = builder().equal(root.get(Colaborador_.EMPRESA), empresa);
        criteria.where(equalEmpresa);
        criteria.orderBy(builder().asc(pathNome));
        TypedQuery<Colaborador> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }


    @Override
    public Class<Colaborador> getClazz() {
        return Colaborador.class;
    }
}
