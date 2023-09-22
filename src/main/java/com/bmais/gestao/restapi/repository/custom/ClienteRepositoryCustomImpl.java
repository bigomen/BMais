package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestPessoaJuridicaPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.QueryHints;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class ClienteRepositoryCustomImpl extends Repository<Cliente> implements ClienteRepositoryCustom {

    private final ContatoRepository contatoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DadosBancariosRepository dadosBancariosRepository;
    private final HospitalClienteRepository hospitalRepository;
    private final ClienteServicosRepository clienteServicosRepository;
    private final DocumentoRepository documentoRepository;

    public ClienteRepositoryCustomImpl(ContatoRepository contatoRepository, UsuarioRepository usuarioRepository,
                                       DadosBancariosRepository dadosBancariosRepository, HospitalClienteRepository hospitalRepository,
                                       ClienteServicosRepository clienteServicosRepository, DocumentoRepository documentoRepository)
    {
        this.contatoRepository = contatoRepository;
        this.usuarioRepository = usuarioRepository;
        this.dadosBancariosRepository = dadosBancariosRepository;
        this.hospitalRepository = hospitalRepository;
        this.clienteServicosRepository = clienteServicosRepository;
        this.documentoRepository = documentoRepository;
    }

    @Override
    public Collection<Cliente> pesquisarCliente(RestPessoaJuridicaPesquisa params) {
        CriteriaQuery<Cliente> criteria = super.getCriteria();
        Root<Cliente> root = criteria.from(getClazz());
        Join<Cliente, StatusPessoaJuridica> joinStatus = root.join(Cliente_.STATUS, JoinType.INNER);
        final Predicate[] conjunction = {builder().conjunction()};

//        Join<Cliente, Hospital> joinHospital = root.join(Cliente_.HOSPITAIS, JoinType.LEFT);

        Path<Long> idCliente = root.get(Cliente_.ID);
        Path<String> razaoSocial = root.get(Cliente_.RAZAO_SOCIAL);
        Path<String> cnpj = root.get(Cliente_.CNPJ);
        Path<BigDecimal> valorAltoCusto = root.get(Cliente_.VALORALTO_CUSTO);
        Path<String> status = joinStatus.get(StatusPessoaJuridica_.DESCRICAO);

        criteria.distinct(true);
        criteria.multiselect(idCliente, razaoSocial, cnpj, valorAltoCusto, status);

        if (StringUtils.isNotBlank(params.getRazaoSocial())) {
            Predicate like = builder().like(builder().upper(root.get(Cliente_.RAZAO_SOCIAL)), like((params.getRazaoSocial().toUpperCase())));
            conjunction[0] = builder().and(conjunction[0], like);
        }
        if (StringUtils.isNotBlank(params.getCnpj())) {
            Predicate like = builder().like(builder().upper(root.get(Cliente_.CNPJ)), like(params.getCnpj().toUpperCase()));
            conjunction[0] = builder().and(conjunction[0], like);
        }
        if (StringUtils.isNotBlank(params.getIdHospital())) {
        	Join<Cliente, HospitalCliente> joinHospital = root.join(Cliente_.HOSPITAIS, JoinType.INNER);
            Predicate equal = builder().equal(joinHospital.get(HospitalCliente_.HOSPITAL).get(Hospital_.ID), UtilSecurity.decryptId(params.getIdHospital()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if (params.getCapeanteProprio() != null) {
            Predicate equal = builder().equal(root.get(Cliente_.CAPEANTE), params.getCapeanteProprio());
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if (params.getValorAltoCusto() != null) {
            Predicate equal = builder().equal(root.get(Cliente_.VALORALTO_CUSTO), params.getValorAltoCusto());
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if (StringUtils.isNotBlank(params.getStatus())) {
            Join<Cliente, StatusPessoaJuridica> joinStatusCliente = root.join(Cliente_.STATUS, JoinType.INNER);
            Predicate equal = builder().equal(joinStatusCliente.get(StatusPessoaJuridica_.ID), Long.parseLong(params.getStatus()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        
//        if(params.getVinculos() != null){
//            params.getVinculos().forEach(v -> {
//                Predicate equal = builder().equal(joinHospital.get(Hospital_.ID), v.getHospitalId());
//                conjunction[0] = builder().and(conjunction[0], equal);
//                equal = builder().equal(root.get(Cliente_.ID), v.getClienteId());
//                conjunction[0] = builder().and(conjunction[0], equal);
//            });
//        }
        criteria.where(conjunction[0]);
        criteria.orderBy(builder().asc(razaoSocial));
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteria);
        typedQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Cliente> pesquisarCliente(Long id) {
        CriteriaQuery<Cliente> criteria = super.getCriteria();
        Root<Cliente> root = criteria.from(getClazz());
        root.fetch(Cliente_.ENDERECO, JoinType.INNER);
        root.fetch(Cliente_.STATUS, JoinType.INNER);

        criteria.where(builder().equal(root.get(Cliente_.ID), id));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteria);

        try
        {
            Cliente cliente = typedQuery.getSingleResult();
            cliente.setUsuarios(usuarioRepository.listaUsuariosCliente(cliente));
            cliente.setContatos(contatoRepository.pesquisarContatosPessoaJuridica(cliente));
            cliente.setDadosBancarios(dadosBancariosRepository.dadosPorPessoaJuridica(cliente));
            cliente.setServicos(clienteServicosRepository.listaServicosCliente(cliente));
            cliente.setDocumentos(documentoRepository.documentosPessoaJuridica(cliente));
            cliente.setHospitais(hospitalRepository.listaHospitaisCliente(cliente));
            return Optional.of(cliente);
        }catch (NoResultException nex)
        {
            return Optional.empty();
        }
    }

    @Override
    public Class<Cliente> getClazz() {
        return Cliente.class;
    }
}
