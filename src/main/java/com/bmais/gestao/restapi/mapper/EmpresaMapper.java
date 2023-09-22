package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.restmodel.RestEmpresa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, DadosBancariosMapper.class, UsuarioMapper.class, StatusEmpresaMapper.class, EnderecoMapper.class})
public interface EmpresaMapper {

    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    RestEmpresa convertToRest(Empresa empresa);

    Empresa convertToModel(RestEmpresa restEmpresa);
}
