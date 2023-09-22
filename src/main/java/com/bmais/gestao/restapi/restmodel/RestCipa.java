package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.CipaMapper;
import com.bmais.gestao.restapi.mapper.GlosaMapper;
import com.bmais.gestao.restapi.model.Cipa;
import com.bmais.gestao.restapi.model.Colaborador;
import com.bmais.gestao.restapi.model.Glosa;
import com.bmais.gestao.restapi.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestCipa extends RestModel<Cipa> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "inicio_gestao")
    private LocalDate inicioGestao;

    @JsonProperty(value = "fim_gestao")
    private LocalDate fimGestao;

    @JsonProperty(value = "inicio_estabilidade")
    private LocalDate inicioEstabilidade;

    @JsonProperty(value = "fim_estabilidade")
    private LocalDate fimEstabilidade;

    @JsonProperty(value = "usuario")
    private RestUsuario usuario;

    @JsonProperty(value = "cadastro")
    private LocalDate cadastro;

    @JsonProperty(value = "tipo")
    private String tipo;

    @Override
    public Cipa restParaModel() {
        return CipaMapper.INSTANCE.convertToModel(this);
    }
}
