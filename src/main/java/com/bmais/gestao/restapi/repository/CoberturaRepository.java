package com.bmais.gestao.restapi.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.repository.custom.CoberturaRepositoryCustom;

@Repository
public interface CoberturaRepository extends CrudRepository<Cobertura, Long>, CoberturaRepositoryCustom {

    @Query("select new Cobertura(c.id) from Cobertura c where c.periodoInicio between :periodoInicio and :periodoFim and c.periodoFim between :periodoInicio and :periodoFim")
//    @Query("select new Cobertura(c.id) from Cobertura c where c.periodoInicio between :periodoInicio and :periodoFim and c.periodoFim between :periodoInicio and :periodoFim and c.cliente.id = :cliente and c.hospital.id = :hospital and c.servico.id = :servico")
    Collection<Cobertura> validarCobertura(LocalDateTime periodoInicio, LocalDateTime periodoFim);
    
    @Query("select c from Cobertura c join fetch c.auditor a where c.vinculo.id = :escala and current_date() between c.periodoInicio and c.periodoFim")
    Optional<Cobertura> pesquisarCobertura(Long escala);
    
    @Query("select c from Cobertura c join fetch c.auditor a where c.vinculo.id = :escala and :dataInicio >= c.periodoInicio and c.periodoFim <= :dataFim")
    Optional<Cobertura> pesquisarCobertura(Long escala, LocalDate dataInicio, LocalDate dataFim);
    
    @Query("select case when count(c.id) > 0 then true else false end from Cobertura c where c.vinculo.id = :escala "
    		+ "and((c.periodoInicio >= :dataInicio and c.periodoFim <= :dataFim) "
    		+ "or (:dataInicio between c.periodoInicio and c.periodoFim) "
    		+ "or (:dataFim between c.periodoInicio and c.periodoFim))")
    Boolean existeCoberturaAuditor(Long escala, LocalDate dataInicio, LocalDate dataFim);
}
