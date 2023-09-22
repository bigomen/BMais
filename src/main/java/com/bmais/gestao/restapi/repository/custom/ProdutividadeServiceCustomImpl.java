package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Produtividade;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutividadeServiceCustomImpl extends com.bmais.gestao.restapi.repository.custom.Repository<Produtividade> implements ProdutividadeRepositoryCustom{


    @Override
    public Class<Produtividade> getClazz() {
        return Produtividade.class;
    }
}
