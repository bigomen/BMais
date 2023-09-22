package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.PagamentosGerados;
import com.bmais.gestao.restapi.restmodel.RestPagamentosGerados;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, PacienteMapper.class, UsuarioMapper.class, EmpresaMapper.class, StatusPagamentosGeradosMapper.class})
public interface PagamentosGeradosMapper {

    PagamentosGeradosMapper INSTANCE = Mappers.getMapper(PagamentosGeradosMapper.class);

    RestPagamentosGerados convertToRest(PagamentosGerados pagamentosGerados);

    PagamentosGerados convertToModel(RestPagamentosGerados restPagamentosGerados);
}
