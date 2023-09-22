package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.repository.ClienteRepository;
import com.bmais.gestao.restapi.repository.EmpresaRepository;
import com.bmais.gestao.restapi.repository.GrupoUsuarioRepository;
import com.bmais.gestao.restapi.restmodel.RestUsuarioPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;

public class UsuarioRepositoryCustomImpl extends Repository<Usuario> implements UsuarioRepositoryCustom {

    private final EmpresaRepository empresaRepository;
    private final GrupoUsuarioRepository grupoUsuarioRepository;

    public UsuarioRepositoryCustomImpl(EmpresaRepository empresaRepository, GrupoUsuarioRepository grupoUsuarioRepository) {
        this.empresaRepository = empresaRepository;
        this.grupoUsuarioRepository = grupoUsuarioRepository;
    }

    @Override
    public Collection<Usuario> lista(RestUsuarioPesquisa params) {
        CriteriaQuery<Usuario> criteria = super.getCriteria();
        Root<Usuario> root = criteria.from(getClazz());
        Predicate conjunction = builder().conjunction();
        Join<Usuario, GrupoUsuario> joinGrupo = root.join(Usuario_.GRUPO, JoinType.INNER);
        Join<Usuario, StatusUsuario> joinStatus = root.join(Usuario_.STATUS);

        Path<Long> idUsuario = root.get(Usuario_.ID);
        Path<Long> idGrupo = joinGrupo.get(GrupoUsuario_.ID);

        Path<String> nome = root.get(Usuario_.NOME);
        Path<String> grupoDescricao = joinGrupo.get(GrupoUsuario_.DESCRICAO);
        Path<String> email = root.get(Usuario_.EMAIL);

        criteria.multiselect(idUsuario, idGrupo, nome, grupoDescricao, email);

        if(params.getCategoria() != null)
        {
            Predicate equalCategoria = builder().equal(joinGrupo.get(GrupoUsuario_.CATEGORIA), params.getCategoria());
            conjunction = builder().and(conjunction, equalCategoria);
        }

        if(StringUtils.isNotBlank(params.getNome())){
            Predicate like = builder().like(builder().upper(root.get(Usuario_.NOME)), like(params.getNome().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }
        if (StringUtils.isNotBlank(params.getEmpresa())){
            Join<Usuario, Empresa> joinEmpresa = root.join(Usuario_.EMPRESAS);
            Predicate equal = builder().equal(joinEmpresa.get(Empresa_.ID), UtilSecurity.decryptId(params.getEmpresa()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getPerfil())){
            Predicate equal = builder().equal(joinGrupo.get(GrupoUsuario_.ID), UtilSecurity.decryptId(params.getPerfil()));
            conjunction = builder().and(conjunction, equal);
        }
        if(StringUtils.isNotBlank(params.getEmail())){
            Predicate like = builder().like(builder().upper(root.get(Usuario_.EMAIL)), like(params.getEmail().toUpperCase()));
            conjunction = builder().and(conjunction, like);
        }

        Predicate notEqual = builder().notEqual(joinStatus.get(StatusUsuario_.ID), StatusUsuario.EXCLUIDO);
        Predicate notEqualMedico = builder().notEqual(joinGrupo.get("id"), GrupoUsuario.AUDITOR_MEDICO);
        Predicate notEqualEnfermeiro = builder().notEqual(joinGrupo.get("id"), GrupoUsuario.AUDITOR_ENFERMEIRO);
        conjunction = builder().and(conjunction, notEqual);
        conjunction = builder().and(conjunction, notEqualMedico);
        conjunction = builder().and(conjunction, notEqualEnfermeiro);

        criteria.where(conjunction);
        criteria.orderBy(builder().asc(nome));
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(criteria);
        Collection<Usuario> usuario = typedQuery.getResultList();
        usuario.forEach(u -> u.setEmpresas(empresaRepository.empresasUsuario(u.getId())));
        return usuario;
    }

    @Override
    public Usuario detalhes(Long id){
       CriteriaQuery<Usuario> criteria = builder().createQuery(Usuario.class);
       Root<Usuario> root = criteria.from(Usuario.class);

       Join<Usuario, GrupoUsuario> joinGrupo = root.join("grupo");
       Join<Usuario, StatusUsuario> joinStatus = root.join("status");

       Path<Long> idUsuario = root.get("id");
       Path<String> nome = root.get("nome");
       Path<String> email = root.get("email");
       Path<Date> dataInicio = root.get("dataInicio");
       Path<Date> dataFim = root.get("dataFim");
       Path<Long> idGrupo = joinGrupo.get("id");
       Path<String> descricaoGrupo = joinGrupo.get("descricao");
       Path<Long> idStatus = joinStatus.get("id");
       Path<String > descricaoStatus = joinStatus.get("descricao");

       criteria.where(builder().equal(idUsuario, id));

       criteria.multiselect(idUsuario, nome, email, dataInicio, dataFim, idGrupo, descricaoGrupo, idStatus, descricaoStatus);

       Usuario usuario = entityManager.createQuery(criteria).getSingleResult();

       usuario.setEmpresas(empresaRepository.empresasUsuario(id));

       return usuario;
    }

    @Override
    public Class<Usuario> getClazz() {
        return Usuario.class;
    }
}
