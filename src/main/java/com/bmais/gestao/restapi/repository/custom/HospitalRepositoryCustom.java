package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.restmodel.RestHospitalPesquisa;

import java.util.Collection;

public interface HospitalRepositoryCustom {

    Collection<Hospital> listar(RestHospitalPesquisa params);

    Hospital detalhes(Long id);

    Collection<Hospital> listaHospitaisVisitaConcorrente(Collection<Long> hospitaisId);
}
