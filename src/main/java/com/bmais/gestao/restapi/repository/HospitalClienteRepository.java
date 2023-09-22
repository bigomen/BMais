package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.model.HospitalCliente;

@Repository
public interface HospitalClienteRepository extends CrudRepository<HospitalCliente, Long>
{
    @Query("select h from HospitalCliente h join fetch h.hospital hos where h.cliente = :cliente order by hos.razaoSocial")
    Collection<HospitalCliente> listaHospitaisCliente(Cliente cliente);
    
    @Query("select h from HospitalCliente h join fetch h.hospital hos where h.cliente.id = :cliente order by hos.razaoSocial")
    Collection<HospitalCliente> listaHospitaisCliente(Long cliente);

    @Query("select DISTINCT c.razaoSocial from HospitalCliente h join h.cliente c join c.status s where h.hospital = :hospital and s.id = :status order by c.razaoSocial")
    Collection<String> listarClientesHospital(Hospital hospital, Long status);

    @Query("select h from HospitalCliente h join fetch h.hospital hos join fetch h.servico ser where h.cliente.id = :cliente and ser.id = :servico order by hos.razaoSocial")
    Collection<HospitalCliente> hospitaisVisitaConcorrente(Long cliente, Long servico);

    Collection<HospitalCliente> findByHospital(Hospital hospital);
}
