package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.TipoPagamentoColaborador;
import com.bmais.gestao.restapi.restmodel.RestTipoPagamentoColaborador;

@Mapper()
public interface TipoPagamentoColaboradorMapper {

    TipoPagamentoColaboradorMapper INSTANCE = Mappers.getMapper(TipoPagamentoColaboradorMapper.class);

    RestTipoPagamentoColaborador convertToRest(TipoPagamentoColaborador tipoPagamentoColaborador);

    TipoPagamentoColaborador convertToModel(RestTipoPagamentoColaborador restTipoPagamentoColaborador);
}
