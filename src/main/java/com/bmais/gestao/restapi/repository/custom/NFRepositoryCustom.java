package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.NF;
import com.bmais.gestao.restapi.restmodel.RestNFPesquisa;

import java.util.Collection;

public interface NFRepositoryCustom {

    Collection<NF> listar(RestNFPesquisa params);
    NF detalhesNF(Long id);

    Collection<NF> pesquisarNotasFiscais(Long empresa, Long dadosBancarios);

}
