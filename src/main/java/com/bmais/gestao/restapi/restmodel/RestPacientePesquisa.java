package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.utility.BaseRuleUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPacientePesquisa extends BaseRuleUtil {

    private String paciente;
    private String cliente;
    private String matricula;
    private String status;
}
