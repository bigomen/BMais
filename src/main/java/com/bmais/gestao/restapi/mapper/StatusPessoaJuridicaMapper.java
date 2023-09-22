package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.StatusPessoaJuridica;
import com.bmais.gestao.restapi.restmodel.RestStatusPessoaJuridica;

@Mapper()
public interface StatusPessoaJuridicaMapper {

    StatusPessoaJuridicaMapper INSTANCE = Mappers.getMapper(StatusPessoaJuridicaMapper.class);

    RestStatusPessoaJuridica convertToRest(StatusPessoaJuridica statusPessoaJuridica);

    StatusPessoaJuridica convertToModel(RestStatusPessoaJuridica restStatusPessoaJuridica);
}
