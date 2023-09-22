package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.repository.custom.ClienteRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>, ClienteRepositoryCustom {

      @Query("select new Cliente (c.id, c.razaoSocial) from Cliente c join c.hospitais h where h.id = :id order by c.razaoSocial")
      Collection<Cliente> pesquisarHospitalCliente(Long id);

//      @Query("select new Cliente(c.status) from Cliente c join c.status s where c.razaoSocial = :razao")
//      Cliente findByRazaoSocial(String razao);
//
//      @Query("select new Cliente(c.status) from Cliente c join c.status s where c.cnpj = :cnpj")
//      Cliente findByCnpj(String cnpj);

      @Query("select new Cliente(c.id, c.razaoSocial) from Cliente c join c.status s where s.id = 1 order by c.razaoSocial")
      Collection<Cliente> listaSimples();

      @Query("select new Cliente(c.id, c.razaoSocial) from Cliente c where c.id = :id")
      Cliente clientesPeloId(Long id);

      @Query("select new Cliente(c.id, c.razaoSocial) from Cliente c where c = :cliente")
      Cliente clienteColaborador(Cliente cliente);

      @Query("select new Cliente(c.id, c.razaoSocial) from Cliente c where c = :cliente")
      Cliente getCliente(Cliente cliente);

      boolean existsByRazaoSocial(String razaoSocial);

      @Query("select CASE WHEN COUNT(c.id) > 0 THEN TRUE ELSE FALSE END from Cliente c  where (c.razaoSocial = :razaoSocial or c.cnpj = :cnpj) and c.id <> :id")
      Boolean razaoCnpjValido(String razaoSocial, String cnpj, Long id);

      boolean existsByCnpj(String cnpj);
      
      @Query("select new Cliente(c.id, c.razaoSocial) from Vinculo v join v.cliente c where c.status.id = 1 order by c.razaoSocial")
      Set<Cliente> pesquisarClientes();

      @Query("select c.prorrogacao from Cliente c where c.id = :id")
      Boolean getProrrogacaoCliente(Long id);

      @Query("select new Cliente(c.id, c.razaoSocial) from Usuario u join u.cliente c where u.id = :id")
      Cliente getClienteUsuario(Long id);
}
