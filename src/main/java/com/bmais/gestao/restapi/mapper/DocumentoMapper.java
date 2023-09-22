package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.restmodel.RestDocumento;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface DocumentoMapper {

    DocumentoMapper INSTANCE = Mappers.getMapper(DocumentoMapper.class);

    RestDocumento convertToRest(Documento documento);

    @Mappings({
            @Mapping(target = "pessoaJuridica", ignore = true),
            @Mapping(target = "colaborador", ignore = true),
            @Mapping(target = "afastamento", ignore = true)
    })
    Documento convertToModel(RestDocumento restDocumento);
}
