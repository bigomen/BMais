package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.FolhaPagamento;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamento;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class})
public interface FolhaPagamentoMapper {

    FolhaPagamentoMapper INSTANCE = Mappers.getMapper(FolhaPagamentoMapper.class);

    RestFolhaPagamento convertToRest(FolhaPagamento folhaPagamento);

    FolhaPagamento convertToModel(RestFolhaPagamento restFolhaPagamento);
}
