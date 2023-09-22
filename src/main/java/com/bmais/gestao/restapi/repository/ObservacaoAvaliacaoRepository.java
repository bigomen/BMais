package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ObservacaoAvaliacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservacaoAvaliacaoRepository extends CrudRepository<ObservacaoAvaliacao, Long> {
}
