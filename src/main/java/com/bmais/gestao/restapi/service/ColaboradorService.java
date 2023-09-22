package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.*;
import com.bmais.gestao.restapi.utility.RuleUtil;
import com.bmais.gestao.restapi.utility.UtilData;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ColaboradorService{

    private final ColaboradorRepository colaboradorRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final CargoRepository cargoRepository;
    private final MotivoDemissaoRepository motivoDemissaoRepository;
    private final TipoValeTransporteRepository tipoValeTransporteRepository;
    private final CipaRepository cipaRepository;
    private final TipoAfastamentoRepository tipoAfastamentoRepository;
    private final FeriasRepository feriasRepository;
    private final DocumentoService documentoService;
    private final StatusColaboradorRepository statusColaboradorRepository;
    private final DadosBancariosService dadosBancariosService;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository, EstadoCivilRepository estadoCivilRepository, CargoRepository cargoRepository, MotivoDemissaoRepository motivoDemissaoRepository, TipoValeTransporteRepository tipoValeTransporteRepository, CipaRepository cipaRepository, TipoAfastamentoRepository tipoAfastamentoRepository, FeriasRepository feriasRepository, DocumentoService documentoService, StatusColaboradorRepository statusColaboradorRepository, DadosBancariosService dadosBancariosService) {
        super();
        this.colaboradorRepository = colaboradorRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.cargoRepository = cargoRepository;
        this.motivoDemissaoRepository = motivoDemissaoRepository;
        this.tipoValeTransporteRepository = tipoValeTransporteRepository;
        this.cipaRepository = cipaRepository;
        this.tipoAfastamentoRepository = tipoAfastamentoRepository;
        this.feriasRepository = feriasRepository;
        this.documentoService = documentoService;
        this.statusColaboradorRepository = statusColaboradorRepository;
        this.dadosBancariosService = dadosBancariosService;
    }

    public Collection<RestColaborador> lista(RestColaboradorPesquisa params){
        Collection<Colaborador> colaboradores = colaboradorRepository.lista(params);
        return colaboradores.stream().map(Colaborador::modelParaRest).collect(Collectors.toList());
    }

    public RestColaborador detalhes(String id){
        if(!colaboradorRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Colaborador colaborador = colaboradorRepository.detalhes(UtilSecurity.decryptId(id));
        colaborador.setMotivoDemissao(motivoDemissaoRepository.getMotivoDemissao(colaborador.getId()));
        RestColaborador restColaborador = colaborador.modelParaRest();
        restColaborador.getDependentes().forEach(d -> d.setNumeroDependente(UtilSecurity.decryptId(d.getId())));
        Optional<String> foto = documentoService.download(colaborador.getFoto());
        foto.ifPresent(f -> {
            String fileMimeType = documentoService.fileMimeType(colaborador.getFoto());
            restColaborador.setFoto(fileMimeType + f);
        });
        return restColaborador;
    }

    public Collection<RestColaborador> pesquisarColaboradoresPorEmpresa(String idEmpresa)
    {
        Collection<Colaborador> colaboradores = colaboradorRepository.pesquisarColaboradoresPorEmpresa(new Empresa(UtilSecurity.decryptId(idEmpresa)));

        return colaboradores.stream()
                .map(Colaborador::modelParaRest)
                .collect(Collectors.toList());
    }

    public void novo(RestColaborador restColaborador){
        if(colaboradorRepository.existsByCpf(restColaborador.getCpf())){
            throw new ObjectAlreadyInBase("CPF já utilizado");
        }
        Colaborador colaborador = restColaborador.restParaModel();

        if(Objects.nonNull(colaborador.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(colaborador.getDadosBancarios());
        }

        colaborador.getEvolucoes().forEach(e ->{
            e.setUsuario(new Usuario(RuleUtil.getUsuarioId()));
            e.setDataAlteracao(UtilData.obterDataAtual());
        } );
        colaborador.getCipas().forEach(c -> {
            c.setUsuario(new Usuario(RuleUtil.getUsuarioId()));
        });
        colaborador.setFerias(null);
        Ferias ferias = new Ferias();
        Collection<Ferias> feriasC = new ArrayList<>();
        ferias.setDataInicio(colaborador.getDataAdmissao().plusYears(1));
        ferias.setDataLimite(colaborador.getDataAdmissao().plusYears(2));
        ferias.setDataFim(ferias.getDataLimite());
        ferias.setStatus(new StatusFerias(StatusFerias.ATIVO));
        ferias.setColaborador(colaborador);
        feriasC.add(ferias);
        colaborador.setFerias(feriasC);
        colaborador.getValesTransporte().forEach(v -> {
            v.setData(restColaborador.getDataAdmissao());
        });

        if(StringUtils.isNotBlank(restColaborador.getFoto())){
            String extension = documentoService.fileExtension(restColaborador.getFoto());
            String nome = "FOTO_".concat(restColaborador.getCpf()).concat(extension);
            colaborador.setFoto(Colaborador.PASTA_DOCUMENTOS + "/" + nome);
            documentoService.upload(Colaborador.PASTA_DOCUMENTOS, nome, restColaborador.getFoto());
        }
        if(colaborador.getDocumentos() != null){
            colaborador.getDocumentos().forEach(d -> {
                String docExtension = documentoService.fileExtension(d.getImagem());
                d.setDescricao(Colaborador.PASTA_DOCUMENTOS + "/" + d.getDescricao() + d.getId() + docExtension);
            });
        }
        if(colaborador.getAfastamentos() != null){
            colaborador.getAfastamentos().forEach(a -> {
                if(a.getDocumentos() != null){
                    a.getDocumentos().forEach(d -> {
                        String docExtension = documentoService.fileExtension(d.getImagem());
                        d.setDescricao(Colaborador.PASTA_DOCUMENTOS + "/" + d.getDescricao() + d.getId() + docExtension);
                    });
                }
            });
        }
        colaborador.setStatus(new StatusColaborador(StatusColaborador.ATIVO));
        colaboradorRepository.save(colaborador);
        if(!colaborador.getDocumentos().isEmpty()){
            documentoService.upload(Colaborador.PASTA_DOCUMENTOS, colaborador.getDocumentos());
        }
        if(!colaborador.getAfastamentos().isEmpty()){
            colaborador.getAfastamentos().forEach(a -> {
                if(!a.getDocumentos().isEmpty()){
                    documentoService.upload(Colaborador.PASTA_DOCUMENTOS, a.getDocumentos());
                }
            });
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RestColaborador atualizar(RestColaborador restColaborador, String id){
        if(!colaboradorRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        if(colaboradorRepository.validarCpf(restColaborador.getCpf(), UtilSecurity.decryptId(id))){
            throw new ObjectAlreadyInBase("CPF já utilizado por outro colaborador");
        }
        String foto = colaboradorRepository.getFoto(UtilSecurity.decryptId(id));
        if(StringUtils.isNotBlank(foto)){
            documentoService.excluir(foto);
        }
        restColaborador.setId(id);
        Colaborador colaborador = restColaborador.restParaModel();

        if(Objects.nonNull(colaborador.getDadosBancarios())){
            dadosBancariosService.validarDadosBancarios(colaborador.getDadosBancarios());
        }

        colaborador.getDocumentos().removeIf(d -> d.getId() == null);
        restColaborador.getDocumentos().forEach(d -> {
            if(d.getId() == null){
                d.setIdentificador(colaborador.getCpf());
                Documento documento = documentoService.salvarDocumento(d);
                documento.setImagem(d.getImagem());
                colaborador.addDocumentos(documento);
            }
        });
        if(StringUtils.isNotBlank(restColaborador.getFoto())){
            String extension = documentoService.fileExtension(restColaborador.getFoto());
            String nome = "FOTO_".concat(restColaborador.getCpf()).concat(extension);
            colaborador.setFoto(Colaborador.PASTA_DOCUMENTOS + "/" + nome);
            documentoService.upload(Colaborador.PASTA_DOCUMENTOS, nome, restColaborador.getFoto());
        }
        colaborador.getDocumentos()
                        .stream()
                                .filter(d -> StringUtils.isNotBlank(d.getImagem()))
                                        .forEach(d -> {
                                            String docExtension = documentoService.fileExtension(d.getImagem());
                                            d.setDescricao(Colaborador.PASTA_DOCUMENTOS + "/" + d.getDescricao() + d.getId() + docExtension);
                                            documentoService.upload(Colaborador.PASTA_DOCUMENTOS, d.getDescricao().substring(d.getDescricao().lastIndexOf("/") + 1), d.getImagem());
                                        });
        if(colaborador.getAfastamentos() != null){
            colaborador.getAfastamentos().forEach(a -> {
                if(a.getDocumentos() != null){
                    a.getDocumentos().stream()
                            .filter(d -> StringUtils.isNotBlank(d.getImagem()))
                            .forEach(d -> {
                                String docExtension = documentoService.fileExtension(d.getImagem());
                                d.setDescricao(Colaborador.PASTA_DOCUMENTOS + "/" + d.getDescricao() + d.getId() + docExtension);
                                documentoService.upload(Colaborador.PASTA_DOCUMENTOS, d.getDescricao().substring(d.getDescricao().lastIndexOf("/") + 1), d.getImagem());
                            });
                }
            });
        }

        colaborador.getEvolucoes().forEach(e ->{
            e.setUsuario(new Usuario(RuleUtil.getUsuarioId()));
            e.setDataAlteracao(UtilData.obterDataAtual());
        });
        colaborador.getCipas().forEach(c -> {
            if(c.getId() == null){
                c.setUsuario(new Usuario(RuleUtil.getUsuarioId()));
            }else {
                c.setUsuario(new Usuario(cipaRepository.getUsuario(c.getId())));
            }
        });
        colaborador.getValesTransporte().forEach(v -> {
            v.setData(colaboradorRepository.getData(v.getId()));
        });
//        colaborador.setFerias(feriasRepository.feriasColab(colaborador.getId()));
        colaborador.getFerias().forEach(f ->{
            if(Objects.equals(f.getStatus().getId(), StatusFerias.ATIVO)){
                Ferias ferias = colaboradorRepository.getFerias(f.getId());
                if(f.getGozoInicio1() != null && f.getGozoFim1() != null){
                    f.setQuantidadeDias(ChronoUnit.DAYS.between(f.getGozoFim1(), f.getGozoInicio1()));
                }
                if(f.getGozoInicio2() != null && f.getGozoFim2() != null){
                    f.setQuantidadeDias(f.getQuantidadeDias() + (ChronoUnit.DAYS.between(f.getGozoFim2(), f.getGozoInicio2())));
                }
                if(f.getGozoInicio3() != null && f.getGozoFim3() != null){
                    f.setQuantidadeDias(f.getQuantidadeDias() + (ChronoUnit.DAYS.between(f.getGozoFim3(), f.getGozoInicio3())));
                }
                f.setDataInicio(ferias.getDataLimite());
                f.setDataLimite(ferias.getDataLimite());
                f.setStatus(ferias.getStatus());
            }
        });
        colaborador.setStatus(new StatusColaborador(StatusColaborador.ATIVO));
        if(restColaborador.getDataDemissao() != null){
            colaborador.setStatus(new StatusColaborador(StatusColaborador.INATIVO));
        }
        colaboradorRepository.save(colaborador);
        return restColaborador;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        if(!colaboradorRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        colaboradorRepository.desativar(UtilSecurity.decryptId(id));
    }

    public Collection<RestColaborador> listaAdministrativo(){
        Collection<Colaborador> colaboradores = colaboradorRepository.listaAdministrativo();
        return colaboradores.stream().map(Colaborador::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestPagamentosGerados> listaPagamentos(String id){
//        Collection<PagamentosGerados> pagamentos = folhaPagamentoColaboradorRepository.listaPagamentos(UtilSecurity.decryptId(id));
        return null;
    }

    public Collection<RestColaborador> listaSimples(){
        Collection<Colaborador> colaboradores = colaboradorRepository.listaSimples();
        return colaboradores.stream().map(Colaborador::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestEstadoCivil> listaEstadoCivil(){
        Collection<EstadoCivil> estadosCivis = (Collection<EstadoCivil>) estadoCivilRepository.findAll();
        return estadosCivis.stream().map(EstadoCivil::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestCargo> listaCargos(){
        Collection<Cargo> cargos = (Collection<Cargo>) cargoRepository.findAll();
        return cargos.stream().map(Cargo::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestMotivoDemissao> listaMotivos(){
        Collection<MotivoDemissao> motivos = (Collection<MotivoDemissao>) motivoDemissaoRepository.findAll();
        return motivos.stream().map(MotivoDemissao::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestTipoValeTransporte> listaTipoValeTransporte(){
        Collection<TipoValeTransporte> tipos = tipoValeTransporteRepository.findAllByOrderByDescricaoAsc();
        return tipos.stream().map(TipoValeTransporte::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestTipoAfastamento> listaTipoAfastamento(){
        Collection<TipoAfastamento> afastamentos = tipoAfastamentoRepository.findAllByOrderByDescricaoAsc();
        return afastamentos.stream().map(TipoAfastamento::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestStatusColaborador> listaStatusColaborador(){
        Collection<StatusColaborador> status = (Collection<StatusColaborador>) statusColaboradorRepository.findAll();
        return status.stream().map(StatusColaborador::modelParaRest).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public Collection<RestCargo> novoCargo(RestCargo restCargo)
	{
		cargoRepository.save(restCargo.restParaModel());
		return listaCargos();
	}
}
