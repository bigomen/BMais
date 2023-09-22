package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.model.enums.TipoCapeante;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class RestCapeantePesquisa {

    private String cliente;
    private String hospital;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInicialFechamento;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataFinalFechamento;

    private String numero;
    private String status;
    private String loteProtocolo;
    private TipoCapeante tipo;
}
