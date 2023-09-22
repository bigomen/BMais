package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Pagamento;
import com.bmais.gestao.restapi.restmodel.RestPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagamentoMapper
{
    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    @Mappings(value = {
            @Mapping(target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(com.bmais.gestao.restapi.utility.UtilSecurity.encryptId(pagamento.id()))"),
            @Mapping(target = "data", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.data())"),
            @Mapping(target = "historico", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.historico())"),
            @Mapping(target = "documento", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.documento())"),
            @Mapping(target = "valor", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.valor())"),
            @Mapping(target = "lancamento", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.lancamento())"),
            @Mapping(target = "saldo", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.saldo())"),
            @Mapping(target = "noBanco", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.noBanco())"),
            @Mapping(target = "movimento", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, expression = "java(pagamento.movimento())")
    })
    public RestPagamento convertToRest(Pagamento pagamento);
}