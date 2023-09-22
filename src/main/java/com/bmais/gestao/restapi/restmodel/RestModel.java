package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.model.BaseModel;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class RestModel<E extends BaseModel> extends BaseRestModel
{
    public abstract E restParaModel();
}
