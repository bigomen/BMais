package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Tuss;
import org.springframework.data.domain.Page;

public interface TussRepositoryCustom {

    Page<Tuss> pesquisar(String codigo, String descricao, Long status, String orderBy, int pagina, int pageSize);
}
