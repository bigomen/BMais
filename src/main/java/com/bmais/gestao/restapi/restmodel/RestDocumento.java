package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.DocumentoMapper;
import com.bmais.gestao.restapi.model.Documento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestDocumento extends RestModel<Documento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String descricao;

    @JsonProperty(value = "validade")
    private LocalDate validade;

    @JsonProperty(value = "tipo")
    private String tipo;

    @JsonProperty(value = "base64")
    private String imagem;

    @JsonIgnore
    @Schema(hidden = true)
    private String identificador;

    @Override
    public Documento restParaModel(){
        return DocumentoMapper.INSTANCE.convertToModel(this);
    }
}
