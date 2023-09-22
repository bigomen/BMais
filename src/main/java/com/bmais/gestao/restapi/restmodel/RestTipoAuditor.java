package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.TipoAuditorMapper;
import com.bmais.gestao.restapi.model.TipoAuditor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestTipoAuditor extends RestModel<TipoAuditor> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "descricao")
    private String descricao;

    @Override
    public TipoAuditor restParaModel(){return TipoAuditorMapper.INSTANCE.convertToModel(this);}
}
