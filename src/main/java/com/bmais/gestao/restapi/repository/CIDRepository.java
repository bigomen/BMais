package com.bmais.gestao.restapi.repository;


import com.bmais.gestao.restapi.model.CID;
import com.bmais.gestao.restapi.repository.custom.CIDRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CIDRepository extends CrudRepository<CID, Long>, CIDRepositoryCustom {

    @Query("select new CID(c.id, c.codigo, c.descricao) from CID c join c.status s where s.id = 1L order by c.descricao")
    Collection<CID> listaSimples();

    @Query("select new CID(c.id, c.descricao) from CID c where c = :cid")
    CID getCid(CID cid);
}
