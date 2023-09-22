package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import com.bmais.gestao.restapi.mapper.PacienteMapper;
import com.bmais.gestao.restapi.model.Paciente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestPaciente extends RestModel<Paciente> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "dataNascimento")
    private LocalDate dataNascimento;

    @JsonProperty(value = "matricula")
    private String matricula;

    @JsonProperty(value = "sexo")
    private String sexo;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "status")
    private RestStatusPaciente status;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "relatorios")
    private Collection<RestRelatorioPaciente> relatorios;
    
    @JsonProperty(value = "dataUltimoProntuario")
    private LocalDate dataUltimoProntuario;
    
    @JsonProperty(value = "dataUltimaVisita")
    private LocalDate dataUltimaVisita;

    public RestPaciente(String id) {
        this.setId(id);
    }

    @Override
    public Paciente restParaModel() {
        return PacienteMapper.INSTANCE.convertToModel(this);
    }
}
