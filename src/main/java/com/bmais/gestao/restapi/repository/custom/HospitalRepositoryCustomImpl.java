package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.ContatoRepository;
import com.bmais.gestao.restapi.repository.HospitalClienteRepository;
import com.bmais.gestao.restapi.restmodel.RestHospitalPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

public class HospitalRepositoryCustomImpl extends Repository<Hospital> implements HospitalRepositoryCustom {

    private final ContatoRepository contatoRepository;
    private final HospitalClienteRepository hospitalClienteRepository;

    @Autowired
    public HospitalRepositoryCustomImpl(ContatoRepository contatoRepository, HospitalClienteRepository hospitalClienteRepository)
    {
        this.contatoRepository = contatoRepository;
        this.hospitalClienteRepository = hospitalClienteRepository;
    }

    @Override
    public Collection<Hospital> listar(RestHospitalPesquisa params){
        CriteriaQuery<Hospital> criteria = super.getCriteria();
        Root<Hospital> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();

        Path<Long> idHospital = root.get(Hospital_.ID);
        Path<String> razaoHospital = root.get(Hospital_.RAZAO_SOCIAL);
        Path<String> cnpj = root.get(Hospital_.CNPJ);
        Path<Boolean> auditavel = root.get(Hospital_.AUDITAVEL);
        Path<StatusHospital> status = root.get(Hospital_.STATUS);

        criteria.multiselect(idHospital, razaoHospital, cnpj, auditavel, status);

        if(StringUtils.isNotBlank(params.getCliente()))
        {
            Join<Hospital, HospitalCliente> joinHospitalCliente = root.join(Hospital_.CLIENTES, JoinType.INNER);
            Predicate equalCliente = builder().equal(joinHospitalCliente.get(HospitalCliente_.CLIENTE).get(Cliente_.ID), UtilSecurity.decryptId(params.getCliente()));
            conjunction = builder().and(conjunction, equalCliente);
        }

        if(StringUtils.isNotBlank(params.getRazaoSocial())){
            Predicate like = builder().like(builder().upper(root.get(Hospital_.RAZAO_SOCIAL)), like(params.getRazaoSocial().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if(StringUtils.isNotBlank(params.getCnpj())){
            Predicate like = builder().like(builder().upper(root.get(Hospital_.CNPJ)), like(params.getCnpj().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if(params.getAuditavel() != null){
            Predicate equal = builder().equal(root.get(Hospital_.AUDITAVEL), params.getAuditavel());
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getStatus())){
            Join<Hospital, StatusHospital> joinStatus = root.join(Hospital_.STATUS, JoinType.INNER);
            Predicate equal = builder().equal(joinStatus.get(Hospital_.ID), Long.parseLong(params.getStatus()));
            conjunction = builder().and(conjunction, equal);
        }
        criteria.where(conjunction);
        criteria.orderBy(builder().asc(razaoHospital));
        TypedQuery<Hospital> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Hospital detalhes(Long id){
        CriteriaQuery<Hospital> criteria = super.getCriteria();
        Root<Hospital> root = criteria.from(getClazz());
        root.fetch(Hospital_.ENDERECO, JoinType.INNER);
        criteria.where(builder().equal(root.get(Hospital_.ID), id));
        TypedQuery<Hospital> typedQuery = entityManager.createQuery(criteria);
        Hospital hospital = typedQuery.getSingleResult();

        Collection<String> clientes = hospitalClienteRepository.listarClientesHospital(hospital, StatusPessoaJuridica.ATIVO);
        if(Objects.nonNull(clientes)){
            Collection<HospitalCliente> cli = new ArrayList<>();
            for (String c : clientes){
                HospitalCliente hos = new HospitalCliente();
                Cliente cliente = new Cliente();
                cliente.setRazaoSocial(c);
                hos.setCliente(cliente);
                cli.add(hos);
            }
            hospital.setClientes(cli);
        }
        hospital.setContatos(contatoRepository.findByHospital(hospital));
        return hospital;
    }

    public Collection<Hospital> listaHospitaisVisitaConcorrente(Collection<Long> hospitaisId){
        CriteriaQuery<Hospital> criteria = super.getCriteria();
        Root<Hospital> root = criteria.from(getClazz());
        List<Predicate> conjunction = new ArrayList<>();

        Path<Long> hospitalId = root.get(Hospital_.ID);
        Path<String> hospitalRazao = root.get(Hospital_.RAZAO_SOCIAL);

        criteria.multiselect(hospitalId, hospitalRazao);

        hospitaisId.forEach(h -> {
            Predicate equal = builder().equal(root.get(Hospital_.ID), h);
            conjunction.add(equal);
        });
        criteria.where(builder().or(conjunction.toArray(new Predicate[0])));
        criteria.orderBy(builder().asc(hospitalRazao));
        TypedQuery<Hospital> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Hospital> getClazz(){return Hospital.class;}
}
