package com.bmais.gestao.restapi.restmodel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCIDPesquisa {

    String codigo;
    String descricao;
    String status;
    String orderBy;
    int pageSize;
}
