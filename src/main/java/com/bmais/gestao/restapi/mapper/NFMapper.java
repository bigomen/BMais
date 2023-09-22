package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.NF;
import com.bmais.gestao.restapi.restmodel.RestNF;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class,DadosBancariosMapper.class, EmpresaMapper.class})
public interface NFMapper {

    NFMapper INSTANCE = Mappers.getMapper(NFMapper.class);

    RestNF convertToRest(NF nf);

    NF convertToModel(RestNF restNF);
}
