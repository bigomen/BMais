package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Paciente;
import com.bmais.gestao.restapi.model.StatusPaciente;
import com.bmais.gestao.restapi.repository.custom.PacienteRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long>, PacienteRepositoryCustom {

    @Query(value = "select p from Paciente p join fetch p.cliente c join fetch p.status s where p.id = :id")
    Optional<Paciente> findById(@Param(value = "id") Long id);

    @Query("select new Paciente(p.id, p.nome) from Paciente p join p.status s where s.id = 1L order by p.nome")
    Collection<Paciente> listaSimples();

    @Query("select new Paciente(p.id, p.nome, p.dataNascimento, p.sexo, p.matricula, e.id, e.razaoSocial) from Paciente p join p.cliente e where p.id = :id")
    Paciente detalhesMesclagem(Long id);

    @Query("select new Paciente(p.id) from Paciente p join p.cliente c join p.status s where p.nome = :nome and p.dataNascimento = :dataNascimento" +
            " and c.id = :idCliente and s.id = 1L")
    Paciente validarCadastro(String nome, LocalDate dataNascimento, Long idCliente);

    @Query("select new Paciente(p.id, p.nome) from Paciente p where p.id = :id")
    Paciente internacaoPaciente(Long id);

    @Query("select new Paciente(p.sexo, c.id) from Paciente p join p.cliente c where p.id = :id")
    Paciente getPacienteMesclagem(Long id);

    @Query("select new Paciente(p.id, p.nome) from Paciente p where p = :paciente")
    Paciente getPaciente(Paciente paciente);

    @Query("select new StatusPaciente(s.id) from Paciente p join p.status s where p.id = :id")
    StatusPaciente getStatus(Long id);
}
