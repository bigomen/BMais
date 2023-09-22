package com.bmais.gestao.restapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.CapeanteInternacao;
import com.bmais.gestao.restapi.repository.custom.CapeanteInternacaoRepositoryCustom;

@Repository
public interface CapeanteInternacaoRepository extends CrudRepository<CapeanteInternacao, Long>, CapeanteInternacaoRepositoryCustom
{
    @Modifying
    @Query("update CapeanteInternacao c set c.status.id = :status where c.id = :id")
    void excluir(Long id, Long status);
}
