package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.CapeantePSAmbulatorioMapper;
import com.bmais.gestao.restapi.model.CapeantePSAmbulatorio;
import com.bmais.gestao.restapi.model.enums.TipoCapeante;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
public class RestCapeantePSAmbulatorio extends RestModel<CapeantePSAmbulatorio> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tipoCapeante")
    private TipoCapeante tipoCapeante;

    @JsonProperty(value = "numeroCapeante")
    private Long numeroCapeante;

    @JsonProperty(value = "loteProtocolo")
    private String loteProtocolo;

    @JsonProperty(value = "status")
    private RestStatusCapeante status;

    @JsonProperty(value = "usuario")
    private RestUsuario usuario;

    @JsonProperty(value = "hospital")
    private RestHospital hospital;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "dataAbertura")
    private LocalDate dataAbertura;

    @JsonProperty(value = "dataFechamento")
    private LocalDate dataFechamento;

    @JsonProperty(value = "auditorMedico")
    private RestAuditor auditorMedico;

    @JsonProperty(value = "auditorEnfermeiro")
    private RestAuditor auditorEnfermeiro;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "resumo")
    private Collection<RestResumoCapeantePSAmbulatorio> resumo;
    
    @JsonProperty(value = "valorCobrado")
    private BigDecimal valorCobrado;
    
    @JsonProperty(value = "valorGlosado")
    private BigDecimal valorGlosado;
    
    @JsonProperty(value = "valorLibarado")
    private BigDecimal valorLibarado;

    @Override
    public CapeantePSAmbulatorio restParaModel(){
        return CapeantePSAmbulatorioMapper.INSTANCE.convertToModel(this);
    }
}
