package com.bmais.gestao.restapi.repository.custom;

import java.util.Collection;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Auditor_;
import com.bmais.gestao.restapi.model.Contato;
import com.bmais.gestao.restapi.model.DadosBancarios;
import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.model.Prestador_;
import com.bmais.gestao.restapi.model.StatusPessoaJuridica;
import com.bmais.gestao.restapi.model.StatusPessoaJuridica_;
import com.bmais.gestao.restapi.repository.AuditorRepository;
import com.bmais.gestao.restapi.repository.ContatoRepository;
import com.bmais.gestao.restapi.repository.DadosBancariosRepository;
import com.bmais.gestao.restapi.repository.DocumentoRepository;
import com.bmais.gestao.restapi.restmodel.RestPrestadorPesquisa;

@org.springframework.stereotype.Repository
public class PrestadorRepositoryCustomImpl extends Repository<Prestador> implements PrestadorRepositoryCustom {

    private final DadosBancariosRepository dadosBancariosRepository;
    private final ContatoRepository contatoRepository;

    private final DocumentoRepository documentoRepository;
    private final AuditorRepository auditorRepository;

    @Autowired
    public PrestadorRepositoryCustomImpl(DadosBancariosRepository dadosBancariosRepository, ContatoRepository contatoRepository,
                                         DocumentoRepository documentoRepository, AuditorRepository auditorRepository) {
        this.dadosBancariosRepository = dadosBancariosRepository;
        this.contatoRepository = contatoRepository;
        this.documentoRepository = documentoRepository;
        this.auditorRepository = auditorRepository;
    }

    @Override
    public Optional<Prestador> detalhes(Long id)
    {
        CriteriaQuery<Prestador> criteria = super.getCriteria();
        Root<Prestador> root = criteria.from(getClazz());
        root.fetch(Prestador_.STATUS, JoinType.INNER);
        root.fetch(Prestador_.ENDERECO, JoinType.INNER);
        criteria.where(builder().equal(root.get(Prestador_.ID), id));

        Prestador fornecedorAuditoria = null;

        try
        {
            TypedQuery<Prestador> typedQuery = entityManager.createQuery(criteria);
            fornecedorAuditoria = typedQuery.getSingleResult();
        }catch (NoResultException nex)
        {
            return Optional.empty();
        }

        Collection<Contato> contatos = contatoRepository.findByPessoaJuridicaOrderByNomeAsc(fornecedorAuditoria);
        fornecedorAuditoria.setContatos(contatos);

        Collection<Documento> documentos = documentoRepository.documentosPessoaJuridica(fornecedorAuditoria);
        fornecedorAuditoria.setDocumentos(documentos);

        Collection<DadosBancarios> dadosBancarios = dadosBancariosRepository.dadosPorPessoaJuridica(fornecedorAuditoria);
        fornecedorAuditoria.setDadosBancarios(dadosBancarios);

        Collection<Auditor> auditores = auditorRepository.findByPrestador(fornecedorAuditoria);
        fornecedorAuditoria.setAuditores(auditores);

        return Optional.of(fornecedorAuditoria);
    }

    @Override
    public Collection<Prestador> pesquisarPrestador(RestPrestadorPesquisa params){
        CriteriaQuery<Prestador> criteria = super.getCriteria();
        Root<Prestador> root = criteria.from(getClazz());
        Join<Prestador, StatusPessoaJuridica> joinStatus = root.join(Prestador_.STATUS, JoinType.INNER);
        Predicate conjunction = builder().conjunction();

        Path<Long> idPrestador = root.get(Prestador_.ID);
        Path<String> razaoSocial = root.get(Prestador_.RAZAO_SOCIAL);
        Path<String> cnpj = root.get(Prestador_.CNPJ);
        Path<String> descStatus = joinStatus.get(StatusPessoaJuridica_.DESCRICAO);

        criteria.multiselect(idPrestador, razaoSocial, cnpj, descStatus);

        if(StringUtils.isNotBlank(params.getRazaoSocial()))
        {
            Predicate like = builder().like(builder().upper(root.get(Prestador_.RAZAO_SOCIAL)), like((params.getRazaoSocial().toUpperCase())));
            conjunction = builder().and(conjunction, like);
        }

        if(StringUtils.isNotBlank(params.getCnpj()))
        {
            Predicate like = builder().like(builder().upper(root.get(Prestador_.CNPJ)), like(params.getCnpj().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }

        if(StringUtils.isNotBlank(params.getStatus()))
        {
            Predicate equal = builder().equal(joinStatus.get(StatusPessoaJuridica_.ID), params.getStatus());
            conjunction = builder().and(conjunction, equal);
        }

        if(StringUtils.isNotBlank(params.getCrmOuCoren()))
        {
            Join<Prestador, Auditor> joinAuditor = root.join(Prestador_.AUDITORES, JoinType.INNER);
            Predicate equalCrmCoren = builder().equal(joinAuditor.get(Auditor_.CRM_COREM), params.getCrmOuCoren());
            conjunction = builder().and(conjunction, equalCrmCoren);
        }

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(razaoSocial));
        TypedQuery<Prestador> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Prestador> getClazz(){return Prestador.class;}
}
