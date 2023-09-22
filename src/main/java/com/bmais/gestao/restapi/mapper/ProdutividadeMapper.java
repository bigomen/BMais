package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Produtividade;
import com.bmais.gestao.restapi.restmodel.RestProdutividade;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, StatusProdutividadeMapper.class, AuditorMapper.class, HospitalMapper.class, VisitaMapper.class, VisitaHomeCareMapper.class, CapeantePSAmbulatorioMapper.class, ServicoMapper.class, UsuarioMapper.class})
public interface ProdutividadeMapper {

    ProdutividadeMapper INSTANCE = Mappers.getMapper(ProdutividadeMapper.class);

    RestProdutividade convertToRest(Produtividade produtividade);

    Produtividade convertToModel(RestProdutividade restProdutividade);
}
