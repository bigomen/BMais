package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.DadosBancariosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Optional;

public class EmpresaRepositoryCustomImpl extends Repository<Empresa> implements EmpresaRepositoryCustom {

    private final DadosBancariosRepository dadosBancariosRepository;

    @Autowired
    public EmpresaRepositoryCustomImpl(DadosBancariosRepository dadosBancariosRepository) {
        this.dadosBancariosRepository = dadosBancariosRepository;
    }

    @Override
    public Optional<Empresa> pesquisarEmpresa(Long id) {
        CriteriaQuery<Empresa> criteria = super.getCriteria();
        Empresa empresa;
        TypedQuery<Empresa> typedQuery = entityManager.createQuery(criteria);
        try {
            empresa = typedQuery.getSingleResult();
        }catch (NoResultException exception){
            return Optional.empty();
        }
        Collection<DadosBancarios> dadosBancarios = dadosBancariosRepository.findByEmpresa(empresa);
        empresa.setDadosBancarios(dadosBancarios);
        return Optional.of(empresa);
    }

    @Override
    public Collection<Empresa> listarEmpresas(){
        CriteriaQuery<Empresa> criteriaQuery = super.getCriteria();
        Root<Empresa> root = criteriaQuery.from(getClazz());

        Join<Empresa, Endereco> joinEmpresaEndereco = root.join(Empresa_.ENDERECO);
        Join<Endereco, Municipio> joinEnderecoMunicipio = joinEmpresaEndereco.join(Endereco_.MUNICIPIO);
        Join<Empresa, Empresa> joinEmpresa = root.join(Empresa_.EMPRESA, JoinType.LEFT);

        Path<Long> empresaId = root.get(Empresa_.ID);
        Path<String> razaoSocial = root.get(Empresa_.RAZAO_SOCIAL);
        Path<String> cnpj = root.get(Empresa_.CNPJ);
        Path<Long> municipioId = joinEnderecoMunicipio.get(Municipio_.ID);
        Path<String> municipio = joinEnderecoMunicipio.get(Municipio_.NOME);
        Path<UF> uf = joinEnderecoMunicipio.get(Municipio_.UF);
        Path<Boolean> sede = root.get(Empresa_.SEDE);
        Path<Long> empresaSede = joinEmpresa.get(Empresa_.ID);

        criteriaQuery.multiselect(empresaId, razaoSocial, cnpj, municipioId, municipio, uf, sede, empresaSede);

        criteriaQuery.orderBy(builder().asc(razaoSocial));

        TypedQuery<Empresa> typedQuery = super.entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Class<Empresa> getClazz() {
        return Empresa.class;
    }
}
