package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ProntuarioMotivoAlta;
import com.bmais.gestao.restapi.restmodel.RestProntuarioMotivoAlta;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ProntuarioMotivoAltaMapper {

    ProntuarioMotivoAltaMapper INSTANCE = Mappers.getMapper(ProntuarioMotivoAltaMapper.class);

    RestProntuarioMotivoAlta convertToRest(ProntuarioMotivoAlta prontuarioMotivoAlta);

    ProntuarioMotivoAlta convertToModel(RestProntuarioMotivoAlta restProntuarioMotivoAlta);

    @Named(value = "toProntuarioMotivoAlta")
    default ProntuarioMotivoAlta toProntuarioMotivoAlta(RestProntuarioMotivoAlta restProntuarioMotivoAlta)
    {
        if(restProntuarioMotivoAlta == null)
        {
            return null;
        }

        if(StringUtils.isBlank(restProntuarioMotivoAlta.getId()))
        {
            return null;
        }
        return new ProntuarioMotivoAlta(UtilSecurity.decryptId(restProntuarioMotivoAlta.getId()));
    }
}
