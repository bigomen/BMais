package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.CategoriaServico;
import com.bmais.gestao.restapi.restmodel.RestCategoriaServico;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface CategoriaServicoMapper {

    CategoriaServicoMapper INSTANCE = Mappers.getMapper(CategoriaServicoMapper.class);

    RestCategoriaServico convertToRest(CategoriaServico categoriaServico);

    @Mapping(target = "servico", ignore = true)
    CategoriaServico convertToModel(RestCategoriaServico restCategoriaServico);

    @Named(value = "toCategoria")
    default CategoriaServico toCategoriaServico(RestCategoriaServico categoria)
    {
        return new CategoriaServico(UtilSecurity.decryptId(categoria.getId()));
    }
}
