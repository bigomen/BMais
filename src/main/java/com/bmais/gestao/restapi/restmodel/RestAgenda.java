package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Size;
import com.bmais.gestao.restapi.mapper.AgendaMapper;
import com.bmais.gestao.restapi.model.Agenda;
import com.bmais.gestao.restapi.model.enums.Periodo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestAgenda extends RestModel<Agenda> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "data")
    private LocalDate data;

    @JsonProperty(value = "tipo")
    private RestTipoAgenda tipo;

    @Size(max = 1)
    @JsonProperty(value = "periodo")
    private Periodo periodo;

    @JsonProperty(value = "dia_semana", access = Access.READ_ONLY)
    private String diaSemana;

    @JsonProperty(value = "contas")
    private Boolean contas;

    @JsonProperty(value = "quant_p")
    private Long quantP;

    @JsonProperty(value = "quant_r")
    private Long quantR;

    @JsonProperty(value = "data_entrega")
    private LocalDate dataEntrega;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "usuario_inclusao", access = Access.READ_ONLY)
    private String usuarioInclusao;

    @JsonProperty(value = "usuario_edicao", access = Access.READ_ONLY)
    private String usuarioEdicao;

    @JsonProperty(value = "data_inclusao", access = Access.READ_ONLY)
    private LocalDate dataInclusao;

    @JsonProperty(value = "data_edicao", access = Access.READ_ONLY)
    private LocalDate dataEdicao;
    
    @JsonProperty(value = "medico")
    private RestVinculo medico;
    
    @JsonProperty(value = "enfermeiro")
    private RestVinculo enfermeiro;
    
    @JsonProperty(value = "colaborador")
    private RestColaborador colaborador;

    @Override
    public Agenda restParaModel()
    {
        return AgendaMapper.INSTANCE.convertToModel(this);
    }
}
