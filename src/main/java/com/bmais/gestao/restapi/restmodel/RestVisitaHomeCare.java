package com.bmais.gestao.restapi.restmodel;


import java.io.Serializable;
import java.time.LocalDate;
import com.bmais.gestao.restapi.mapper.VisitaHomeCareMapper;
import com.bmais.gestao.restapi.model.VisitaHomeCare;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestVisitaHomeCare extends RestModel<VisitaHomeCare> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "data_inclusao")
    private LocalDate dataInclusao;

    @JsonProperty(value = "usuario")
    private String usuario;

    @JsonProperty(value = "status")
    private RestStatusVisita status;

    @JsonProperty(value = "numeroVisita")
    private Long numeroVisita;

    @JsonProperty(value = "prontuario")
    private RestProntuarioVisitaHomeCare prontuario;

    @JsonProperty(value = "auditoria")
    private RestAuditoriaVisitaHomeCare auditoria;

    @Override
    public VisitaHomeCare restParaModel(){
        return VisitaHomeCareMapper.INSTANCE.convertToModel(this);
    }
}
