package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bmais.gestao.restapi.model.StatusProntuarioVisitaHomeCare;
import com.bmais.gestao.restapi.restmodel.RestStatusProntuarioVisitaHomeCare;

@Mapper()
public interface StatusProntuarioVisitaHomeCareMapper {

    StatusProntuarioVisitaHomeCareMapper INSTANCE = Mappers.getMapper(StatusProntuarioVisitaHomeCareMapper.class);

    RestStatusProntuarioVisitaHomeCare convertToRest(StatusProntuarioVisitaHomeCare statusProntuarioVisitaHomeCare);

    StatusProntuarioVisitaHomeCare convertToModel(RestStatusProntuarioVisitaHomeCare restStatusProntuarioVisitaHomeCare);
}
