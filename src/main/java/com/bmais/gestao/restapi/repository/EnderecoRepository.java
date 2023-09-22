package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
