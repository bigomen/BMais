package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.restmodel.RestServico;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, CategoriaServicoMapper.class})
public interface ServicoMapper {

    ServicoMapper INSTANCE = Mappers.getMapper(ServicoMapper.class);

    @Mappings(value = {
            @Mapping(target = "categoriaServicos", source = "categorias")
    })
    RestServico convertToRest(Servico servico);

    @Named(value = "toRestServico")
    @Mappings(value = {
            @Mapping(target = "categoriaServicos", ignore = true)
    })
    RestServico convertToSimpleServico(Servico serico);

    @Mappings({
            @Mapping(target = "clientes", ignore = true),
            @Mapping(target = "categorias", source = "categoriaServicos")
    })
    Servico convertToModel(RestServico restServico);

    @Named(value = "toServico")
    default Servico toServico(RestServico servico)
    {
        return new Servico(UtilSecurity.decryptId(servico.getId()));
    }
}
