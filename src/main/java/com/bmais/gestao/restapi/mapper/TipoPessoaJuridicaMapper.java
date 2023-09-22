package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoPessoaJuridica;
import com.bmais.gestao.restapi.restmodel.RestTipoPessoaJuridica;

@Mapper()
public interface TipoPessoaJuridicaMapper {

    TipoPessoaJuridicaMapper INSTANCE = Mappers.getMapper(TipoPessoaJuridicaMapper.class);

    RestTipoPessoaJuridica convertToRest(TipoPessoaJuridica tipoPessoaJuridica);

    TipoPessoaJuridica convertToModel(RestTipoPessoaJuridica restTipoPessoaJuridica);
}
