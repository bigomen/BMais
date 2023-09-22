package com.bmais.gestao.restapi.restmodel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPrestadorPesquisa {

    private String razaoSocial;
    private String cnpj;
    private String status;
    private String crmOuCoren;
}
