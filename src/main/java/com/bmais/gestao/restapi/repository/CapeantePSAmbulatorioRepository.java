package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.CapeantePSAmbulatorio;
//import com.bmais.gestao.restapi.model.HistoricoCapeante;
import com.bmais.gestao.restapi.model.ProcedimentoCapeantePSAmbulatorio;
import com.bmais.gestao.restapi.repository.custom.CapeantePSAmbulatorioRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface CapeantePSAmbulatorioRepository extends CrudRepository<CapeantePSAmbulatorio, Long>, CapeantePSAmbulatorioRepositoryCustom {

//    @Query("select new HistoricoCapeante(h.id, u.id, u.email, h.tipoHistorico, h.data) from HistoricoCapeante h join h.usuario u join h.capeante c where c.id = :id")
//    Collection<HistoricoCapeante> getHistorico(Long id);

//    @Query("select new ProcedimentoCapeantePSAmbulatorio(pr.id, pr.nomePaciente, pr.valorCobrado, pr.valorGlosado, pr.valorLiberado) from ProcedimentoCapeantePSAmbulatorio pr where pr.capeantePSAmbulatorio.id = :id")
//    Collection<ProcedimentoCapeantePSAmbulatorio> getProcedimentos(Long id);
//
//    @Query("select c.numero from CapeantePSAmbulatorio c where c.id = :id")
//    String findNumero(Long id);
//
//    @Query("select c.dataAbertura from CapeantePSAmbulatorio c where c.id = :id")
//    LocalDate findDataAbertura(Long id);

    @Modifying
    @Query("update CapeantePSAmbulatorio c set c.status.id = :status where c.id = :id")
    void alterar(Long id, Long status);

    @Query("select s.id from CapeantePSAmbulatorio c join c.status s where c.id = :id")
    Long getStatus(Long id);
}
