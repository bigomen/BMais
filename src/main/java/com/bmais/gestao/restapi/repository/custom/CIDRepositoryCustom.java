package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.CID;
import com.bmais.gestao.restapi.restmodel.RestCIDPesquisa;
import org.springframework.data.domain.Page;

public interface CIDRepositoryCustom {

    Page<CID> lista(int pagina, RestCIDPesquisa params);
}
