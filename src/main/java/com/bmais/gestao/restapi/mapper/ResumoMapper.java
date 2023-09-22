package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Resumo;
import com.bmais.gestao.restapi.restmodel.RestResumoCapeanteInternacao;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ResumoMapper {

    ResumoMapper INSTANCE = Mappers.getMapper(ResumoMapper.class);

    RestResumoCapeanteInternacao convertToRest(Resumo resumo);

    Resumo convertToModel(RestResumoCapeanteInternacao resumo);
}
