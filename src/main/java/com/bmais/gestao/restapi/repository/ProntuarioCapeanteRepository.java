package com.bmais.gestao.restapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.ProntuarioCapeante;
import com.bmais.gestao.restapi.repository.custom.ProntuarioCapeanteRepositoryCustom;

@Repository
public interface ProntuarioCapeanteRepository extends CrudRepository<ProntuarioCapeante, Long>, ProntuarioCapeanteRepositoryCustom {

//    @Query("select new CapeanteInternacao(c.dataAbertura, c.numero, s.descricao) from CapeanteInternacao c join c.status s where c.paciente.id = :id")
//    Collection<CapeanteInternacao> getRelatorio(Long id);
//
    @Modifying
    @Query("update ProntuarioCapeante c set c.paciente.id = :idPrincipal where c.paciente.id = :id")
    void alterarPaciente(Long id, Long idPrincipal);
//
//    @Query("select d from Diaria d join d.capeante c join d.acomodacao a where c.id = :id")
//    Collection<Diaria> getDiarias(Long id);
//
//    @Query("select new Resumo(r.id, r.valorApresentado, r.valorGlosa, g.id, g.descricao) from Resumo r join r.capeante c join r.tipoGlosa g where c.id = :id")
//    Collection<Resumo> getResumos(Long id);
//
//    @Query("select p from ProcedimentoCapeante p join p.capeante c join p.tuss t where c.id = :id")
//    Collection<ProcedimentoCapeante> getProcedimentos(Long id);
//
//    @Query("select new HistoricoCapeante(h.id, u.id, u.email, h.tipoHistorico, h.data) from HistoricoCapeante h join h.usuario u join h.capeante c where c.id = :id")
//    Collection<HistoricoCapeante> getHistorico(Long id);
//
    @Query("select new ProntuarioCapeante(c.id, u.id) from ProntuarioCapeante c join c.usuario u where c.id = :id")
    ProntuarioCapeante dadosRelatorio(Long id);
//
//    @Query("select c.numero from CapeanteInternacao c where c.id = :id")
//    String findNumero(Long id);
//
//    @Query("select c.dataAbertura from CapeanteInternacao c where c.id = :id")
//    LocalDate findDataAbertura(Long id);
//
    @Modifying
    @Query("update ProntuarioCapeante c set c.status.id = 3L where c.id = :id")
    void desativar(Long id);


}
