/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.repository.custom.EmpresaRepositoryCustom;

/**
 *
 * @author rcerqueira
 */
@Repository
public interface EmpresaRepository extends CrudRepository<Empresa,Long> , EmpresaRepositoryCustom {
//    List<Empresa> findByEmpStatus(String emp_status);

    @Query("select e from Empresa e order by e.id desc")
    Collection<Empresa> listar();

    Optional<Empresa> findById(Long id);

    @Query("select e from Empresa e where e.sede = true order by e.razaoSocial")
    Collection<Empresa> listarSedes();

    @Query("select e from Empresa e where e.empresa.id = :id order by e.razaoSocial")
    Collection<Empresa> listarFiliais(@Param(value = "id") Long id);

    @Query("select new Empresa(e.id) from Empresa e join e.status s where s.id = 1L and e.cnpj = :cnpj")
    Empresa findByCnpj(String cnpj);

    @Query("select new Empresa(e.id) from Empresa e join e.status s where  s.id = 1L and e.razaoSocial = :razao")
    Empresa findByRazaoSocial(String razao);

    @Query("select new Empresa(e.id, e.razaoSocial) from Empresa e join e.usuarios u where u.id = :id order by e.razaoSocial")
    Collection<Empresa> empresasUsuario(Long id);

    @Query("select new Empresa(e.id, e.razaoSocial) from Empresa e join e.status s where s.id = 1L order by e.razaoSocial")
    Collection<Empresa> listaSimples();

    @Query("select new Empresa(e.id, e.razaoSocial) from Empresa e where e = :empresa order by e.razaoSocial")
    Empresa empresaDoColaborador(Empresa empresa);

    @Query(value = "select distinct new Empresa(e.id, e.razaoSocial) from Empresa e join e.status s join e.dadosBancarios d where s.id = 1L order by e.razaoSocial")
    Collection<Empresa> pesquisarEmpresasComDadosBancarios();

    @Query("select CASE WHEN count(e.id) > 0 THEN true ELSE false END from Empresa e where (e.razaoSocial = :razaoSocial or e.cnpj = :cnpj) and e.id <> :id")
    Boolean validarRazaoCnpj(String razaoSocial, String cnpj, Long id);

    @Query("select CASE WHEN count(e.id) > 0 THEN true ELSE false END from Empresa e where e.razaoSocial = :razaoSocial or e.cnpj = :cnpj")
    Boolean validarCadastro(String razaoSocial, String cnpj);
}
