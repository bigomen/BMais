package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import java.util.Set;

import com.bmais.gestao.restapi.model.CategoriaServico;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.model.enums.ClientePrestador;
import com.bmais.gestao.restapi.repository.custom.ServicoRepositoryCustom;

@Repository
public interface ServicoRepository extends CrudRepository<Servico, Long>, ServicoRepositoryCustom {

    boolean existsByCodigoAndClientePrestador (String codigo, ClientePrestador clientePrestador);

    @Query("select new Servico(s.id, s.descricao, cat.id, cat.categoria, cat.valor) from Servico s join s.categorias cat join s.clientes cl where cl = :cliente")
    Collection<Servico> servicosPorCliente(Cliente cliente);

    @Query("select new Servico(ser.id, ser.descricao, ser.codigo) from Servico ser join ser.status s where s.id = :status order by ser.descricao")
    Collection<Servico> listaSimples(Long status);
    
    @Query("select new Servico(ser.id, ser.descricao, ser.codigo) from Vinculo v join v.servico ser where ser.status.id = 1"
    		+ " and v.cliente.id = :cliente and v.hospital.id = :hospital order by ser.descricao")
    Set<Servico> pesquisarServicosVinculos(Long cliente, Long hospital);

    @Query("select DISTINCT new Servico(ser.id, ser.descricao, ser.codigo) from ClienteServico cs join cs.cliente c join cs.servico ser where c.id = :id order by ser.descricao")
    Collection<Servico> clienteListaSimples(Long id);

    @Modifying
    @Query("delete from Vinculo v where v.categoriaServico = :categoriaServico")
    void apagarVinculo(CategoriaServico categoriaServico);

    @Modifying
    @Query("update from CategoriaServico cs set cs.categoria = cs.categoria * -1 where cs.servico = :servico")
    void atualizarCategoria(Servico servico);
}
