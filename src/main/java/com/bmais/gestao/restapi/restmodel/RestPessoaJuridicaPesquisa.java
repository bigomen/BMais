package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.utility.BaseRuleUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestPessoaJuridicaPesquisa extends BaseRuleUtil {

    private String razaoSocial;
    private String cnpj;
    private Boolean capeanteProprio;
    private BigDecimal valorAltoCusto;
    private String idHospital;
    private String status;
}
