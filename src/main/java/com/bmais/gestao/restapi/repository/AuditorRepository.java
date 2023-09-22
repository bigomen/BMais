package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.repository.custom.AuditorRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface AuditorRepository extends CrudRepository<Auditor,Long>, AuditorRepositoryCustom
{
    @Query("select new Auditor(a.id, a.nome) from Auditor a join a.status s where s.id = 1L order by a.nome")
    Collection<Auditor> lista();

    @Query("select new Auditor(a.id, a.nome) from Auditor a where a.id = :id")
    Auditor auditorAuditoria(Long id);

    @Query("select new Auditor(a.id, a.nome) from Auditor a where a.tipoAuditor = 1 and a.status.id = 1L order by a.nome")
    Collection<Auditor> medicosListaSimples();

    @Query("select new Auditor(a.id, a.nome) from Auditor a where a.tipoAuditor = 2 and a.status.id = 1L order by a.nome")
    Collection<Auditor> enfermeirosListaSimples();

    @Query("select new Auditor(a.id, a.nome) from Auditor a where a.id = :id")
    Auditor getAuditor(Long id);

    @Query("select new Auditor(a.id, a.nome) from Visita v join v.auditor a where v.id = :id order by a.nome")
    Collection<Auditor> auditoresVisita(Long id);

    @Query("select new Auditor(a.id, a.nome) from Visita v join v.auditor a join v.internacao i join i.hospital h ")
    Boolean existsByUsuario(Usuario usuario);

    @Query("select new Auditor(a.id, a.nome) from Vinculo v join v.cliente c join v.hospital h join v.servico s join v.auditor a " +
            "where c.id = :clienteId and h.id = :hospitalId and s.id = :servicoId and a.status.id = 1L and a.prestador.status.id = 1L order by a.nome")
    Collection<Auditor> auditoresVisitaConcorrente(Long hospitalId, Long clienteId, Long servicoId);

    @Query("select new Auditor(a.id, a.nome) from Vinculo v join v.cliente c join v.servico s join v.auditor a " +
            "where c.id = :idCliente and s.id = :servicoId and a.status.id = 1L and a.prestador.status.id = 1L order by a.nome")
    Collection<Auditor> auditoresVisitaHomeCare(Long idCliente, Long servicoId);

    @Query("select new Auditor(a.id, a.nome, a.tipoAuditor) from Vinculo v join v.cliente c join v.hospital h join v.servico s " +
            "join v.auditor a where h.id = :idHospital and c.id = :idCliente and s.id = :idServico and a.status.id = 1L and a.prestador.status.id = 1L order by a.nome")
    Collection<Auditor> auditoresCapeanteInternacao(Long idHospital, Long idCliente, Long idServico);

    @Query("select CASE WHEN count(a.id) > 0 THEN TRUE ELSE FALSE END from Auditor a join a.status s where a.email = :email and s.id = :status")
    Boolean existsByEmail(String email, Long status);

    @Query("select CASE WHEN count(a.id) > 0 THEN TRUE ELSE FALSE END from Auditor a join a.status s where a.id <> :id and a.email = :email and s.id = :status")
    Boolean existsByEmailUpdate(String email, Long status, Long id);

    @Modifying
    @Query("update Auditor a set a.status.id = :statusAuditor where a.prestador.id = :idPrestador")
    void inativarAuditoresPrestador(Long idPrestador, Long statusAuditor);

    @Query("select u.id from Auditor a join a.prestador p join a.usuario u where p.id = :idPrestador")
    Collection<Long> usuariosPrestador(Long idPrestador);

    @Query("select CASE WHEN count(a.id) > 0 THEN TRUE ELSE FALSE END from Auditor a join a.prestador p join a.status s join a.tipoAuditor ta" +
            " where p.id = :idPrestador and ta.id = :tipo" +
            " and s.id = :status and (a.crmCorem = :crm or a.cpf = :cpf or a.rg = :rg)")
    Boolean validarDadosAuditorNovo(Long idPrestador, String crm, String cpf, String rg, Long status, Long tipo);

    @Query("select CASE WHEN count(a.id) > 0 THEN TRUE ELSE FALSE END from Auditor a join a.prestador p join a.status s join a.tipoAuditor ta" +
            " where p.id = :idPrestador and a.id <> :idAuditor and ta.id = :tipo" +
            " and s.id = :status and (a.crmCorem = :crm or a.cpf = :cpf or a.rg = :rg)")
    Boolean validarDadosAuditorAtualizar(Long idPrestador, Long idAuditor,String crm, String cpf, String rg, Long status, Long tipo);
}
