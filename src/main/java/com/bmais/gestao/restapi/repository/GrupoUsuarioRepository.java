package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bmais.gestao.restapi.model.GrupoUsuario;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface GrupoUsuarioRepository extends CrudRepository<GrupoUsuario, Long> {

    @Query("select g from GrupoUsuario g where g.id <> :medico and g.id <> :enfermeiro order by g.descricao")
    Collection<GrupoUsuario> listar(Long medico, Long enfermeiro);

    @Query("select new GrupoUsuario(g.id, g.descricao) from GrupoUsuario g where g.id <> :medico and g.id <> :enfermeiro order by g.descricao")
    Collection<GrupoUsuario> listaSimples(Long medico, Long enfermeiro);

    @Query("select new GrupoUsuario(g.id, g.descricao) from Usuario u join u.grupo g where u.id = :id")
    GrupoUsuario getGrupo(Long id);

//    @Query("select g from Usuario u join u.grupoUsuario g where u.id = :id")
//    GrupoUsuario getGrupoUsuario(Long id);
    
    //@Query("SELECT DISTINCT(obj) FROM GrupoUsuario obj JOIN FETCH obj.perfis ORDER BY obj.gusNome")
    //List<GrupoUsuario> findGrupoUsuarioWithPerfil();

//    public List<GrupoUsuario> findByEmpId(Long empId);


//    public Optional<GrupoUsuario> findByIdAndEmpId(Long id, Long empId);
//    public List<GrupoUsuario> findByEmpIdOrderByGusNome(Long empId);

//    @Modifying
//    @Query(value = "delete from grp_usuario where gus_id=:grpId", nativeQuery = true)
//    public void deleteId(@Param("grpId") Long grpId);
    



}
