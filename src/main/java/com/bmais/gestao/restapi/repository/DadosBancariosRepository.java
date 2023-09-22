package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Banco;
import com.bmais.gestao.restapi.model.DadosBancarios;
import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.model.PessoaJuridica;
import com.bmais.gestao.restapi.repository.custom.DadosBancariosRepositoryCustom;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DadosBancariosRepository extends CrudRepository<DadosBancarios, Long> , DadosBancariosRepositoryCustom {

    Collection<DadosBancarios> findByPessoaJuridica(PessoaJuridica pessoaJuridica);

    @Query(value = "select new DadosBancarios(d.id, b.id, b.nome, d.agencia, d.conta) from DadosBancarios d join d.banco b where d.empresa = :empresa order by b.nome")
    Collection<DadosBancarios> findByEmpresa(@Param(value = "empresa") Empresa empresa);

    @Query("select new DadosBancarios(d.id, b.id, b.nome, d.agencia, d.conta) from DadosBancarios d join d.banco b where d.pessoaJuridica = :pessoaJuridica order by b.nome")
    Collection<DadosBancarios> dadosPorPessoaJuridica(PessoaJuridica pessoaJuridica);

    @Query("select new DadosBancarios(d.id, b.id, b.nome, d.agencia, d.conta, d.tipoContaBancaria) from DadosBancarios d join d.banco b where d.colaborador.id = :id")
    DadosBancarios bancoColaborador(Long id);

    @Query("select CASE WHEN count(d.id) > 0 THEN true ELSE false END from DadosBancarios d where d.conta = :conta and d.agencia = :agencia and d.banco = :banco")
    Boolean validarContaCriacao(String conta, String agencia, Banco banco);

    @Query("select CASE WHEN count(d.id) > 0 THEN true ELSE false END from DadosBancarios d where d.conta = :conta and d.agencia = :agencia and d.banco = :banco and d.id <> :id")
    Boolean validarContaAtualizacao(String conta, String agencia, Banco banco, Long id);
}
