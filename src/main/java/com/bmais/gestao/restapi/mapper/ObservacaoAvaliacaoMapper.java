package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.ObservacaoAvaliacao;
import com.bmais.gestao.restapi.restmodel.RestObservacaoAvaliacao;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface ObservacaoAvaliacaoMapper {

    ObservacaoAvaliacaoMapper INSTANCE = Mappers.getMapper(ObservacaoAvaliacaoMapper.class);

    RestObservacaoAvaliacao convertToRest(ObservacaoAvaliacao observacaoAvaliacao);

    ObservacaoAvaliacao convertToModel(RestObservacaoAvaliacao restObservacaoAvaliacao);
}
