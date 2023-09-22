package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.DadosBancarios;
import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.restmodel.RestDadosBancarios;
import com.bmais.gestao.restapi.restmodel.RestEmpresa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, EnderecoMapper.class, BancoMapper.class})
public interface DadosBancariosMapper {

    DadosBancariosMapper INSTANCE = Mappers.getMapper(DadosBancariosMapper.class);

    @Mappings(value = {
            @Mapping(target = "matriz", source = "empresa"),
            @Mapping(target = "matriz.endereco", ignore = true),
            @Mapping(target = "matriz.empresa", ignore = true),
            @Mapping(target = "matriz.status", ignore = true),
            @Mapping(target = "matriz.dadosBancarios", ignore = true)
    })
    RestDadosBancarios convertToRest(DadosBancarios dadosBancarios);

    @Mappings({
    		@Mapping(target = "empresa", source = "matriz", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toEmpresa"),
            @Mapping(target = "pessoaJuridica", ignore = true),
            @Mapping(target = "endereco", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    })
    DadosBancarios convertToModel(RestDadosBancarios restDadosBancarios);
    
    @Named(value = "toDadosBancarios")
    default DadosBancarios toDadosBancarios(RestDadosBancarios restDadosBancarios)
    {
    	if(restDadosBancarios == null || StringUtils.isBlank(restDadosBancarios.getId()))
    	{
    		return null;
    	}
    	
    	return new DadosBancarios(UtilSecurity.decryptId(restDadosBancarios.getId()));
    }

    @Named(value = "toEmpresa")
    default Empresa toEmpresa(RestEmpresa restEmpresa)
    {
        return new Empresa(UtilSecurity.decryptId(restEmpresa.getId()));
    }
}
