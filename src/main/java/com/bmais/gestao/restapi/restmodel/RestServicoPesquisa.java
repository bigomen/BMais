package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.model.enums.ClientePrestador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestServicoPesquisa {

    private String codigo;
    private String descricao;
    private ClientePrestador clientePrestador;
    private String status;
}
