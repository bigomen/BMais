package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ProntuarioMotivoAltaMapper;
import com.bmais.gestao.restapi.model.ProntuarioMotivoAlta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestProntuarioMotivoAlta extends RestModel<ProntuarioMotivoAlta> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public ProntuarioMotivoAlta restParaModel(){
        return ProntuarioMotivoAltaMapper.INSTANCE.convertToModel(this);
    }
}
