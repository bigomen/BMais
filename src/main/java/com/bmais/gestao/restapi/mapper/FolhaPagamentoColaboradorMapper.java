package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.FolhaPagamentoColaborador;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaborador;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, ColaboradorMapper.class})
public interface FolhaPagamentoColaboradorMapper {

    FolhaPagamentoColaboradorMapper INSTANCE = Mappers.getMapper(FolhaPagamentoColaboradorMapper.class);

    RestFolhaPagamentoColaborador convertToRest(FolhaPagamentoColaborador folhaPagamentoColaborador);

    FolhaPagamentoColaborador convertToModel(RestFolhaPagamentoColaborador restFolhaPagamentoColaborador);
}
