package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ClienteServico;
import com.bmais.gestao.restapi.restmodel.RestClienteServico;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, ServicoMapper.class, CategoriaServicoMapper.class})
public interface ClienteServicoMapper
{

    ClienteServicoMapper INSTANCE = Mappers.getMapper(ClienteServicoMapper.class);

    @Named(value = "toRestServicoCliente")
    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "servico.categoriaServicos", ignore = true)
    })
    RestClienteServico convertToRest(ClienteServico clienteServico);

    @Named(value = "toClienteServico")
    @Mappings(value = {
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    ClienteServico toClienteServico(RestClienteServico servico);
}
