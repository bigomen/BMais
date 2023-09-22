package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.CategoriaServico;
import com.bmais.gestao.restapi.model.Servico;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoriaServicoRepository extends CrudRepository<CategoriaServico, Long> {

//    Collection<CategoriaServico> findByServicoId(Long id);
//    @Query("select c from CategoriaServico c where c.servicos.id = :id")
//    Collection<CategoriaServico> listarCategoriaServico(@Param(value = "id") Long id);

    @Modifying
    @Query("delete from CategoriaServico cs where cs.servico = :servico")
    void excluirCategorias(Servico servico);

    @Modifying
    @Query("update from CategoriaServico cs set cs.categoria = :categoria where cs.id = :id")
    void atualizarCategoria(Integer categoria, Long id);
}
