package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import java.util.Optional;

import com.bmais.gestao.restapi.model.CategoriaServico;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Vinculo;

@Repository
public interface VinculosRepository extends CrudRepository<Vinculo, Long>, VinculosRepositoryCustom {

    @Query("select new Vinculo(v.id, c.id, c.razaoSocial, h.id, h.razaoSocial, s.id, s.descricao, ca.id, ca.categoria, v.dataInicio, v.dataFim) " +
            "from Auditor a join a.vinculos v join v.cliente c join v.hospital h join v.servico s join v.categoriaServico ca where a.id = :id order by c.razaoSocial")
    Collection<Vinculo> getVinculos(Long id);
    
    @Query("select v from Vinculo v join fetch v.auditor a where v.id = :id")
    Optional<Vinculo> pesquisarVinculoauditor(Long id);
    
    @Query("select v from Vinculo v join fetch v.auditor a where a.tipoAuditor.id = :tipoAuditor and v.cliente.id = :cliente " +
            "and v.hospital.id = :hospital and v.servico.id = :servico order by a.nome")
    Collection<Vinculo> pesquisarVinculos(Long cliente, Long hospital, Long servico, Long tipoAuditor);

    @Query("select DISTINCT new Vinculo(c.razaoSocial, a.nome, s.descricao, s.codigo) from Vinculo v join v.cliente c " +
            "join c.status stc join v.auditor a join a.status sta join v.servico s where v.hospital.id = :id " +
            "and stc.id = :statusCliente and sta.id = :statusAuditor order by c.razaoSocial, a.nome, s.descricao")
    Collection<Vinculo> hospitalVinculos(Long id, Long statusCliente, Long statusAuditor);

    @Modifying
    @Query("delete from Vinculo v where v.categoriaServico = :categoriaServico")
    void apagarVinculo(CategoriaServico categoriaServico);
}
