package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Banco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface BancoRepository extends CrudRepository<Banco, Long>
{
}
