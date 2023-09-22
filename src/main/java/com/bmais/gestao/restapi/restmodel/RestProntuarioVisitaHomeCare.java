package com.bmais.gestao.restapi.restmodel;


import java.io.Serializable;
import java.time.LocalDate;

import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.mapper.ProntuarioVisitaHomeCareMapper;
import com.bmais.gestao.restapi.model.ProntuarioVisitaHomeCare;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestProntuarioVisitaHomeCare extends RestModel<ProntuarioVisitaHomeCare> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "numero")
    private Long numero;
    
    @JsonProperty(value = "tipo_homecare")
    private String tipoHomecare;

    @JsonProperty(value = "paciente")
    private RestPaciente paciente;

    @JsonProperty(value = "empresa")
    private String empresa;

    @JsonProperty(value = "inicio")
    private LocalDate inicio;

    @JsonProperty(value = "alta")
    private LocalDate alta;

    @JsonProperty(value = "motivo_alta")
    private RestProntuarioMotivoAlta motivoAlta;

    @JsonProperty(value = "hospital")
    private String hospital;

    @JsonProperty(value = "endereco")
    private RestEndereco endereco;
    
    @JsonProperty(value = "status")
    private RestStatusProntuarioVisitaHomeCare status;

    @JsonProperty(value = "obito")
    private Boolean obito;
    
    @JsonProperty(value = "usuario")
    private String usuario;
    
    @JsonProperty(value = "dataInclusao")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataInclusao;

    public ProntuarioVisitaHomeCare restParaModel(){
        return ProntuarioVisitaHomeCareMapper.INSTANCE.convertToModel(this);
    }
}
