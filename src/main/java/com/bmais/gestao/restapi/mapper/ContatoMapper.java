package com.bmais.gestao.restapi.mapper;


import com.bmais.gestao.restapi.model.Contato;
import com.bmais.gestao.restapi.restmodel.RestContato;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ContatoMapper {

    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);
    
    RestContato convertToRest(Contato contato);

    @Mappings({
            @Mapping(target = "pessoaJuridica", ignore = true),
            @Mapping(target = "hospital", ignore = true),
            @Mapping(target = "colaborador", ignore = true),
            @Mapping(target = "financeiro", defaultValue = "false", source = "financeiro"),
            @Mapping(target = "recebeEmail", defaultValue = "false", source = "recebeEmail")
    })
    Contato convertToModel(RestContato restContato);
}
