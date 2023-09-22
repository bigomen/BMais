package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.ClienteServico;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.model.Servico;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface ClienteServicosRepository extends CrudRepository<ClienteServico, Long> {
    @Query("select s from ClienteServico s join fetch s.servico ser where s.cliente = :cliente order by s.cliente.razaoSocial")
    Collection<ClienteServico> listaServicosCliente(Cliente cliente);

    @Modifying
    @Query("delete from ClienteServico s where s.cliente = :cliente")
    void excluirServicos(Cliente cliente);

    @Query("select cs.id from ClienteServico cs where cs.cliente = :cliente and cs.servico = :servico and :data BETWEEN cs.dataInicio AND cs.dataFim")
    Long validarPeriodoServico(LocalDate data, Servico servico, Cliente cliente);
}
