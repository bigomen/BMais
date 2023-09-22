package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.AvaliacaoRelatorio;
import com.bmais.gestao.restapi.restmodel.RestAvaliacaoRelatorio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface AvaliacaoRelatorioMapper {

    AvaliacaoRelatorioMapper INSTANCE = Mappers.getMapper(AvaliacaoRelatorioMapper.class);

    @Named(value = "toRestAvaliacaoVisitaConcorrente")
    default RestAvaliacaoRelatorio convertToRest(AvaliacaoRelatorio avaliacaoRelatorio)
    {
    	RestAvaliacaoRelatorio rest = new RestAvaliacaoRelatorio();
    	rest.setId(UtilSecurity.encryptId(avaliacaoRelatorio.getId()));
    	rest.setDescricao(avaliacaoRelatorio.getDescricao());
    	rest.setMarcado(avaliacaoRelatorio.getVisitas() == null ? false : true);
    	return rest;
    }

    AvaliacaoRelatorio convertToModel(RestAvaliacaoRelatorio restAvaliacaoRelatorio);
}
