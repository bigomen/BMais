package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Long>{

    Collection<Contato> findByPessoaJuridica(Fornecedor fornecedorServicoProduto);
    Collection<Contato> findByPessoaJuridicaOrderByNomeAsc(Prestador fornecedorAuditoria);
    Collection<Contato> findByHospital(Hospital hospital);

    @Query("select new Contato(c.id, c.nome, c.telefone, c.celular, c.cargo, c.email, c.financeiro) from Contato c where c.hospital = :hospital order by c.nome")
    Collection<Contato> pesquisarContatosHospital(Hospital hospital);

    @Query("select new Contato(c.id, c.nome, c.telefone, c.celular, c.cargo, c.email, c.financeiro) from Contato c where c.pessoaJuridica = :pessoaJuridica order by c.nome")
    Collection<Contato> pesquisarContatosPessoaJuridica(PessoaJuridica pessoaJuridica);

}
