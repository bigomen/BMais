package com.bmais.gestao.restapi.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestVisitaHomecarePesquisa {

    private String paciente;
    private String cliente;
    private String hospital;
    private String matriculaPaciente;
    private String status;
    private String prontuario;
}
