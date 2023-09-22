package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.InternacaoMapper;
import com.bmais.gestao.restapi.model.Internacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RestInternacao extends RestModel<Internacao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "paciente")
    private RestPaciente paciente;

    @JsonProperty(value = "numero_internacao")
    private Long numeroInternacao;

    @JsonProperty(value = "dataHora")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    @JsonProperty(value = "dataHoraAlta")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraAlta;

    @JsonProperty(value = "obito")
    private Boolean obito;

    @JsonProperty(value = "hospital")
    private RestHospital hospital;

    @JsonProperty(value = "senha")
    private String senha;

    @JsonProperty(value = "pendenciaRelatorio")
    private Boolean pendenciaRelatorio;

    
    @JsonProperty(value = "status")
    private RestStatusInternacao status;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "diasLiberados")
    private Integer diasLiberados;

    public RestInternacao(String id){
        this.setId(id);
    }

    @Override
    public Internacao restParaModel() {
        return InternacaoMapper.INSTANCE.convertToModel(this);
    }
}
