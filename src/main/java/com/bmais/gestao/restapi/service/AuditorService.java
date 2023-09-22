package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.mapper.AuditorMapper;
import com.bmais.gestao.restapi.mapper.VinculoAuditorMapper;
import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.*;
import com.bmais.gestao.restapi.restmodel.RestAuditor;
import com.bmais.gestao.restapi.restmodel.RestDocumento;
import com.bmais.gestao.restapi.restmodel.RestTipoAuditor;
import com.bmais.gestao.restapi.restmodel.RestVinculo;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuditorService {

    private final AuditorRepository auditorRepository;
    private final TipoAuditorRepository tipoauditorRepository;
    private final DocumentoService documentoService;
    private final VinculosRepository vinculoRepository;
    private final HospitalRepository hospitalRepository;
    private final InternacaoRepository internacaoRepository;
    private final ProntuarioVisitaHomeCareRepository prontuarioVisitaHomeCareRepository;
    private final ProntuarioCapeanteRepository prontuarioCapeanteRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public AuditorService(AuditorRepository auditorRepository, TipoAuditorRepository tipoauditorRepository,
                          DocumentoService documentoService, VinculosRepository vinculoRepository, HospitalRepository hospitalRepository, InternacaoRepository internacaoRepository, ProntuarioVisitaHomeCareRepository prontuarioVisitaHomeCareRepository, ProntuarioCapeanteRepository prontuarioCapeanteRepository, UsuarioService usuarioService) {
        this.auditorRepository = auditorRepository;
        this.tipoauditorRepository = tipoauditorRepository;
        this.documentoService = documentoService;
		this.vinculoRepository = vinculoRepository;
        this.hospitalRepository = hospitalRepository;
        this.internacaoRepository = internacaoRepository;
        this.prontuarioVisitaHomeCareRepository = prontuarioVisitaHomeCareRepository;
        this.prontuarioCapeanteRepository = prontuarioCapeanteRepository;
        this.usuarioService = usuarioService;
    }

    public Collection<RestAuditor> lista(){
        Collection<Auditor> auditores = auditorRepository.lista();
        return auditores.stream().map(Auditor::modelParaRest).collect(Collectors.toList());
    }
    
    public Map<String, List<RestVinculo>> pesquisarAuditoresVinculos(String cliente, String hospital, String servico)
    {
    	Collection<Vinculo> medicos = vinculoRepository.pesquisarVinculos(UtilSecurity.decryptId(cliente), UtilSecurity.decryptId(hospital), UtilSecurity.decryptId(servico), TipoAuditor.MEDICO);
    	Collection<Vinculo> enfermeiros = vinculoRepository.pesquisarVinculos(UtilSecurity.decryptId(cliente), UtilSecurity.decryptId(hospital), UtilSecurity.decryptId(servico), TipoAuditor.ENFERMEIRO);
    	
    	Map<String, List<RestVinculo>> auditores = new HashMap<>();
    	
    	if(!medicos.isEmpty())
    	{
    		List<RestVinculo> restMedicos = medicos.stream().map(VinculoAuditorMapper.INSTANCE :: convertToAuditorVinculoRest).collect(Collectors.toList());
    		auditores.put("MEDICOS", restMedicos);
    	}
    	
    	if(!enfermeiros.isEmpty())
    	{
    		List<RestVinculo> restEnferemeiros = enfermeiros.stream().map(VinculoAuditorMapper.INSTANCE :: convertToAuditorVinculoRest).collect(Collectors.toList());
    		auditores.put("ENFERMEIROS", restEnferemeiros);
    	}
    	
    	return auditores;
    }

    public RestAuditor detalhar(String id)
    {
        Auditor auditor = auditorRepository.pesquisarAuditor(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException("Auditor não encontrado"));
        RestAuditor restAuditor = auditor.modelParaRest();
        Optional<String> assinatura = documentoService.download(auditor.getAssinatura());
        assinatura.ifPresent(ass ->{
            String fileMimeType = documentoService.fileMimeType(auditor.getAssinatura());
            restAuditor.setAssinatura(fileMimeType + ass);
        });

        return restAuditor;

    }

    public Collection<RestAuditor> medicosListaSimples(){
        Collection<Auditor> auditores = auditorRepository.medicosListaSimples();
        return auditores.stream().map(Auditor::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestAuditor> enfermeirosListaSimples(){
        Collection<Auditor> auditores = auditorRepository.enfermeirosListaSimples();
        return auditores.stream().map(Auditor::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestTipoAuditor> listaTiposAuditores()
    {
        Collection<TipoAuditor> all = tipoauditorRepository.findAllByOrderByDescricao();
        return all.stream()
                .map(TipoAuditor::modelParaRest)
                .collect(Collectors.toList());
    }
    
    public Map<String, List<RestAuditor>> auditoresPorHospital(String hospital)
    {
    	Collection<Auditor> auditores = auditorRepository.pesquisarAuditorPorHospital(hospital);
    	
    	return auditores.stream()
    			.map(AuditorMapper.INSTANCE::convertToRest)
    			.collect(Collectors.groupingBy(r -> r.getTipoAuditor().getDescricao()));
    			
    }

    private void validarServicos(Collection<Vinculo> vinculos){
        Collection<Vinculo> vinc = new ArrayList<>(vinculos);
        for(Vinculo v : vinculos){
            vinc.remove(v);
            for(Vinculo vi : vinc){
                if(Objects.isNull(v.getDataInicio()) || Objects.isNull(vi.getDataInicio())){
                    throw new IncorrectData("Data Inicio obrigatorio!");
                }
                if(Objects.equals(v.getCliente().getId(), vi.getCliente().getId()) && Objects.equals(v.getHospital().getId(), vi.getHospital().getId())
                        && Objects.equals(v.getServico().getId(), vi.getServico().getId())){
                    if(Objects.isNull(v.getDataFim()) && Objects.isNull(vi.getDataFim())){
                        throw new IncorrectData("Não permitido dois vinculos ou mais iguais sem Data Fim !");
                    }else if(Objects.isNull(v.getDataFim()) && Objects.nonNull(vi.getDataFim())){
                        if(v.getDataInicio().isBefore(vi.getDataFim())){
                            throw new IncorrectData("Vinculos iguais não podem compreender o mesmo periodo!");
                        }
                    }else if(Objects.nonNull(v.getDataFim()) && Objects.isNull(vi.getDataFim())){
                        if(vi.getDataInicio().isBefore(v.getDataFim())){
                            throw new IncorrectData("Vinculos iguais não podem compreender o mesmo periodo!");
                        }
                    }else{
                        if((v.getDataInicio().isAfter(vi.getDataInicio()) && v.getDataInicio().isBefore(vi.getDataFim()))
                                || (v.getDataFim().isAfter(vi.getDataInicio()) && v.getDataFim().isBefore(vi.getDataFim()))){
                            throw new IncorrectData("Vinculos iguais não podem compreender o mesmo periodo!");
                        }else if((vi.getDataInicio().isAfter(v.getDataInicio()) && vi.getDataInicio().isBefore(v.getDataFim()))
                                || (vi.getDataFim().isAfter(v.getDataInicio()) && vi.getDataFim().isBefore(v.getDataFim()))){
                            throw new IncorrectData("Vinculos iguais não podem compreender o mesmo periodo!");
                        }
                    }
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestAuditor restAuditor) throws MessagingException {
        if(auditorRepository.existsByEmail(restAuditor.getEmail(), StatusAuditor.ATIVO)){
            throw new ObjectAlreadyInBase("Email já utilizado por outro auditor");
        }
        if(usuarioService.validarEmailUsuarioAuditor(restAuditor.getEmail())){
            throw new ObjectAlreadyInBase("Email já utilizado por outro usuario");
        }

        if(auditorRepository.validarDadosAuditorNovo(UtilSecurity.decryptId(restAuditor.getPrestador().getId()),
                restAuditor.getCrmCorem(),
                restAuditor.getCpf(),
                restAuditor.getRg(),
                StatusAuditor.ATIVO,
                Long.valueOf(restAuditor.getTipoAuditor().getId()))){
            throw new ObjectAlreadyInBase("Não é possivel registrar em um mesmo prestador um auditor com o mesmo CPF/RG/CRM/Tipo que esteja ativo");
        }

        Auditor auditor = restAuditor.restParaModel();

        if(Objects.nonNull(auditor.getVinculos())){
            validarServicos(auditor.getVinculos());
        }

        if(StringUtils.isNotBlank(restAuditor.getAssinatura()))
        {
            String extension = documentoService.fileExtension(restAuditor.getAssinatura());
            String nome = "ASS_".concat(restAuditor.getCrmCorem()).concat(extension);
            auditor.setAssinatura(Auditor.PASTA_DOCUMENTOS + "/" + nome);
            documentoService.upload(Auditor.PASTA_DOCUMENTOS, nome, restAuditor.getAssinatura());
        }

        auditor = auditorRepository.save(auditor);

        auditor.getDocumentos().forEach(doc -> {
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Auditor.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
        });

        Usuario usuarioAuditor = new Usuario();
        usuarioAuditor.setNome(auditor.getNome());
        usuarioAuditor.setEmail(auditor.getEmail());
        usuarioAuditor.setDataInicio(LocalDate.now());
        if(Objects.equals(auditor.getStatus().getId(), StatusAuditor.ATIVO)) usuarioAuditor.setStatus(new StatusUsuario(StatusUsuario.ATIVO));
        if(Objects.equals(auditor.getStatus().getId(), StatusAuditor.INATIVO)) usuarioAuditor.setStatus(new StatusUsuario(StatusUsuario.INATIVO));
        if(Objects.equals(auditor.getTipoAuditor().getId(), TipoAuditor.MEDICO)) usuarioAuditor.setGrupo(new GrupoUsuario(GrupoUsuario.AUDITOR_MEDICO));
        if(Objects.equals(auditor.getTipoAuditor().getId(), TipoAuditor.ENFERMEIRO)) usuarioAuditor.setGrupo(new GrupoUsuario(GrupoUsuario.AUDITOR_ENFERMEIRO));
        Long userId = usuarioService.novoUsuarioAuditor(usuarioAuditor);
        auditor.setUsuario(new Usuario(userId));

        auditorRepository.save(auditor);

        if(!auditor.getDocumentos().isEmpty())
        {
            documentoService.upload(Auditor.PASTA_DOCUMENTOS, auditor.getDocumentos());
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(String id, RestAuditor restAuditor)
    {
        Auditor auditor = auditorRepository.findById(UtilSecurity.decryptId(id)).orElseThrow(() ->new ObjectNotFoundException("Auditor não localizado"));
        if(auditorRepository.existsByEmailUpdate(restAuditor.getEmail(), StatusAuditor.ATIVO, auditor.getId())){
            throw new ObjectAlreadyInBase("Email já utilizado por outro auditor");
        }
        if(Objects.nonNull(auditor.getUsuario())){
            if(usuarioService.validarEmailUsuarioAuditor(restAuditor.getEmail(), auditor.getUsuario().getId())){
                throw new ObjectAlreadyInBase("Email já utilizado por outro usuario");
            }
        }

        if(auditorRepository.validarDadosAuditorAtualizar(UtilSecurity.decryptId(restAuditor.getPrestador().getId()),
                UtilSecurity.decryptId(id),
                restAuditor.getCrmCorem(),
                restAuditor.getCpf(),
                restAuditor.getRg(),
                StatusAuditor.ATIVO,
                Long.valueOf(restAuditor.getTipoAuditor().getId()))){
            throw new ObjectAlreadyInBase("Não é possivel registrar em um mesmo prestador um auditor com o mesmo CPF/RG/CRM/Tipo que esteja ativo");
        }

        restAuditor.setId(id);
        Auditor novoAuditor = restAuditor.restParaModel();

        if(Objects.nonNull(novoAuditor.getVinculos())){
            validarServicos(novoAuditor.getVinculos());
        }

        if(StringUtils.isNotBlank(auditor.getAssinatura()))
        {
            documentoService.excluir(auditor.getAssinatura());
        }

        novoAuditor.getDocumentos().removeIf(doc -> doc.getId() == null);

        for (RestDocumento doc : restAuditor.getDocumentos())
        {
            if(doc.getId() == null)
            {
                doc.setIdentificador(novoAuditor.getCrmCorem());
                Documento documento = documentoService.salvarDocumento(doc);
                documento.setImagem(doc.getImagem());
                novoAuditor.getDocumentos().add(documento);
            }
        }

        if(StringUtils.isNotBlank(restAuditor.getAssinatura()))
        {
            String extension = documentoService.fileExtension(restAuditor.getAssinatura());
            String nome = "ASS_".concat(restAuditor.getCrmCorem()).concat(extension);
            novoAuditor.setAssinatura(Auditor.PASTA_DOCUMENTOS + "/" + nome);
            documentoService.upload(Auditor.PASTA_DOCUMENTOS, nome, restAuditor.getAssinatura());
        }
        
        novoAuditor.getDocumentos()
        .stream()
        .filter(doc -> StringUtils.isNotBlank(doc.getImagem()))
        .forEach(doc ->{
            String docExtension = documentoService.fileExtension(doc.getImagem());
            doc.setDescricao(Auditor.PASTA_DOCUMENTOS + "/" + doc.getDescricao() + doc.getId() + docExtension);
            documentoService.upload(Auditor.PASTA_DOCUMENTOS, doc.getDescricao().substring(doc.getDescricao().lastIndexOf("/") + 1), doc.getImagem());
        });

        if(Objects.nonNull(auditor.getUsuario())){
            if(Objects.equals(novoAuditor.getStatus().getId(), StatusAuditor.ATIVO)) usuarioService.setStatusUsuarioAuditor(auditor.getUsuario().getId(), StatusUsuario.ATIVO);
            if(Objects.equals(novoAuditor.getStatus().getId(), StatusAuditor.INATIVO)) usuarioService.setStatusUsuarioAuditor(auditor.getUsuario().getId(), StatusUsuario.INATIVO);
        }

        auditorRepository.save(novoAuditor);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        Optional<Auditor> optionalAuditor = auditorRepository.findById(UtilSecurity.decryptId(id));
        Auditor auditor = optionalAuditor.orElseThrow(() -> new ObjectNotFoundException("Auditor não localizado"));
        auditorRepository.delete(auditor);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void inativarAuditoresPrestador(Long idPrestador){
        auditorRepository.inativarAuditoresPrestador(idPrestador, StatusAuditor.INATIVO);
        Collection<Long> userIds = auditorRepository.usuariosPrestador(idPrestador);
        if(Objects.nonNull(userIds)){
            usuarioService.desativarUsuariosPrestador(userIds);
        }
    }

    public Collection<RestAuditor> listaAuditoresVisitaConcorrente(String idInternacao){
        Long hospitalId = hospitalRepository.hospitalInternacao(UtilSecurity.decryptId(idInternacao));
        Collection<Auditor> auditores = auditorRepository.pesquisarAuditorPorHospital(UtilSecurity.encryptId(hospitalId));
        return auditores.stream().map(Auditor::modelParaRestVisita).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Collection<RestAuditor> auditoresVisitaConcorrente(String internacaoId){
        Optional<Internacao> internacao = internacaoRepository.findById(UtilSecurity.decryptId(internacaoId));
        if(internacao.isEmpty()){
            throw new ObjectNotFoundException("Internação não encontrada");
        }
        Long idCliente = internacao.get().getPaciente().getCliente().getId();
        Long idHospital = internacao.get().getHospital().getId();
        Collection<Auditor> auditores = auditorRepository.auditoresVisitaConcorrente(idHospital, idCliente, Servico.VISITA_CONCORRENTE_P);
        return auditores.stream().map(Auditor::modelParaRest).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Collection<RestAuditor> auditoresVisitaHomeCare(String prontuarioId){
        Optional<ProntuarioVisitaHomeCare> prontuario = prontuarioVisitaHomeCareRepository.findById(UtilSecurity.decryptId(prontuarioId));
        if(prontuario.isEmpty()){
            throw new ObjectNotFoundException("Prontuario não encontrado!");
        }
        Long idCliente = prontuario.get().getPaciente().getCliente().getId();
        Collection<Auditor> auditores = auditorRepository.auditoresVisitaHomeCare(idCliente, Servico.VISITA_HOMECARE_P);
        return auditores.stream().map(Auditor::modelParaRest).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, List<RestAuditor>> auditoresCapeanteInternacao(String prontuarioId){
        Optional<ProntuarioCapeante> prontuario = prontuarioCapeanteRepository.findById(UtilSecurity.decryptId(prontuarioId));
        if(prontuario.isEmpty()){
            throw new ObjectNotFoundException("Prontuario não encontrado!");
        }
        Long idCliente = prontuario.get().getPaciente().getCliente().getId();
        Long idHospital = prontuario.get().getHospital().getHospital().getId();
        Collection<Auditor> auditores = auditorRepository.auditoresCapeanteInternacao(idHospital, idCliente, Servico.CAPEANTE_INTERNACAO_P);
        return auditores.stream()
                .map(AuditorMapper.INSTANCE::convertToRest)
                .collect(Collectors.groupingBy(r -> r.getTipoAuditor().getDescricao()));

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, List<RestAuditor>> auditoresCapeantePS(String idCliente, String idHospital){
        Collection<Auditor> auditores = auditorRepository.auditoresCapeanteInternacao(UtilSecurity.decryptId(idHospital), UtilSecurity.decryptId(idCliente), Servico.CAPEANTE_PS_P);
        return auditores.stream()
                .map(AuditorMapper.INSTANCE::convertToRest)
                .collect(Collectors.groupingBy(r -> r.getTipoAuditor().getDescricao()));
    }
}
