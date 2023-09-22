package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Paciente;
import com.bmais.gestao.restapi.restmodel.RestPacientePesquisa;

import java.util.Collection;

public interface PacienteRepositoryCustom {

    Collection<Paciente> lista(RestPacientePesquisa params);
    Paciente detalhes(Long id);
	Collection<Paciente> listaInternados(RestPacientePesquisa params);

    Collection<Paciente> listaPacientesVisitaConcorrente(Collection<Long> hospitais);
}
