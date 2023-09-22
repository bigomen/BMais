package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ProdutividadeMapper;
import com.bmais.gestao.restapi.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class RestProdutividade extends RestModel<Produtividade> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "auditor")
    private RestAuditor auditor;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "hospital")
    private RestHospital hospital;

    @JsonProperty(value = "visitaConcorrente")
    private RestVisita visitaConcorrente;

    @JsonProperty(value = "visitaHomeCare")
    private RestVisitaHomeCare visitaHomeCare;

//    @JsonProperty(value = "capeanteInternacao")
//    private RestCapeanteInternacao capeanteInternacao;

    @JsonProperty(value = "capeantePSAmbulatorio")
    private RestCapeantePSAmbulatorio capeantePSAmbulatorio;
    @JsonProperty(value = "servico")
    private RestServico servico;

    @JsonProperty(value = "competencia")
    private String competencia;

    @JsonProperty(value = "status")
    private RestStatusProdutividade status;

    @JsonProperty(value = "usuarioLancamento")
    private RestUsuario usuarioLancamento;

    @JsonProperty(value = "dataLancamento")
    private LocalDate dataLancamento;

    @JsonProperty(value = "usuarioEdicao")
    private RestUsuario usuarioEdicao;

    @JsonProperty(value = "dataEdicao")
    private LocalDate dataEdicao;

    @Override
    public Produtividade restParaModel(){
        return ProdutividadeMapper.INSTANCE.convertToModel(this);
    }
}
