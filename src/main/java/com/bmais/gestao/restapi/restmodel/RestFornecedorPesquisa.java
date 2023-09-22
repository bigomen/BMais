package com.bmais.gestao.restapi.restmodel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestFornecedorPesquisa {

    private String razaoSocial;
    private String cnpj;
    private String ramoAtividade;
    private String status;
}
