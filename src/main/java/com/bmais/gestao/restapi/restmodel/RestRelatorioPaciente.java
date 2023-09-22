package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.model.enums.TipoRelatorio;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestRelatorioPaciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "tipo")
    private TipoRelatorio tipo;

    @JsonProperty(value = "numero")
    private Long numero;

    @JsonProperty(value = "data")
    private LocalDate data;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "acoes")
    private String acoes;

    public RestRelatorioPaciente(Long id, TipoRelatorio tipo, LocalDate data, Long numero, String descricao){
        this.id = UtilSecurity.encryptId(id);
        this.setTipo(tipo);
        this.setData(data);
        this.setNumero(numero);
        this.setStatus(descricao);
    }
}
