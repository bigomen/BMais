/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.repository.custom.UsuarioRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author rcerqueira
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>, UsuarioRepositoryCustom {

    @Query(value = "select u from Usuario u join fetch u.grupo g join fetch u.empresas f " +
            "join fetch u.status s where u.id = :id")
    Optional<Usuario> findById(@Param(value = "id") Long id);

    Boolean existsByNome(String nome);

    @Query("select CASE WHEN count(u.id) > 0 THEN TRUE ELSE FALSE END from Usuario u join u.status s where u.email = :email and s.id = :status")
    Boolean existsByEmail(String email, Long status);

    @Query("select CASE WHEN count(u.id) > 0 THEN TRUE ELSE FALSE END from Usuario u join u.status s where u.id <> :id and u.email = :email and s.id = :status")
    Boolean existsByEmail(String email, Long status, Long id);

    @Query("select u.senha from Usuario u where u.id = :id")
    String getSenha(Long id);

    @Modifying
    @Query("update Usuario u set u.status.id = 3L,u.email = :email where u.id = :id")
    void desativar(Long id, String email);

    @Modifying
    @Query("update Usuario u set u.resetPasswordToken = :token where u.email = :email")
    void setPassToken(String token, String email);

    @Modifying
    @Query("update Usuario u set u.cliente = null where u.cliente.id = :clienteId")
    void limpaUsuarios(Long clienteId);

    @Modifying
    @Query("update Usuario u set u.senha = :senha, u.resetPasswordToken = :nulo where u.resetPasswordToken = :token")
    void setNovaSenha(String senha, String token, String nulo);

    @Modifying
    @Query("update Usuario u set u.cliente.id = :idCliente where u.id = :idUsuario")
    void associaUsuarioCliente(Long idCliente, Long idUsuario);

    @Query(value = "select u from Usuario u join fetch u.grupo g left join fetch u.auditor a left join fetch a.vinculos v where u.email = :email")
    Optional<Usuario> encontrarUsuario(@Param(value = "email") String email);

    @Query("select new Usuario(u.id, u.nome, u.email, u.status) from Usuario u where u.cliente = :cliente order by u.nome")
    Collection<Usuario> listaUsuariosCliente(Cliente cliente);

    @Query("select u.id from Usuario u join u.cliente c where c.id = :id")
    Collection<Long> idsUsuarioCliente(Long id);

    Boolean existsByResetPasswordToken(String token);

    @Query("select new Usuario(u.id, u.nome, u.email) from Usuario u where u.id = :id")
    Usuario usuarioVisita(Long id);

    @Query("select new Usuario(u.id, u.email) from Usuario u where u = :usuario")
    Usuario getUsuario(Usuario usuario);

    @Query("select new Usuario(u.id, u.nome, u.email) from Usuario u join u.status s where s.id = :status order by u.nome")
    Collection<Usuario> listaSimples(Long status);

    @Query("select CASE WHEN count(u.id) > 0 THEN true ELSE false END from Usuario u where u.email = :email and u.id <> :id")
    Boolean validarUpdate(String email, Long id);

    @Query("select s.id from Usuario u join u.status s where u.id = :id")
    Long getUsuarioStatus(Long id);

    @Query("select CASE WHEN count(u.id) > 0 THEN true ELSE false END from Usuario u join u.status s where u.email = :email and s.id <> 3L")
    Boolean validarUsuarioByEmail(String email);

    @Modifying
    @Query("update Usuario u set u.status.id = :statusUsuario where u.id = :id")
    void inativarUsuario(Long id, Long statusUsuario);

    @Modifying
    @Query("update Usuario u set u.status.id = :status where u.id = :id")
    void atualizarStatusUsuarioAuditor(Long id, Long status);
}
