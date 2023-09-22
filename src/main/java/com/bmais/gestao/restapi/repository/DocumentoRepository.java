package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.model.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface DocumentoRepository extends CrudRepository<Documento, Long> {

    @Query("select new Documento(d.id, d.descricao, d.validade, d.tipo) from Documento d where d.pessoaJuridica = :pessoaJuridica order by d.descricao")
    Collection<Documento> documentosPessoaJuridica(PessoaJuridica pessoaJuridica);

    @Query("select new Documento(d.id, d.descricao, d.validade, d.tipo) from Documento d where d.colaborador.id = :id order by d.descricao")
    Collection<Documento> documentoColaborador(Long id);

    @Query("select new Documento(d.id, d.descricao, d.validade, d.tipo) from Documento d where d.afastamento.id = :id order by d.descricao")
    Collection<Documento> documentoAfastamento(Long id);
}
