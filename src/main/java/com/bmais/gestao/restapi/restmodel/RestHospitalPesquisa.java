package com.bmais.gestao.restapi.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestHospitalPesquisa {

    private String razaoSocial;
    private String cnpj;
    private Boolean auditavel;
    private String status;
    private String cliente;
}
