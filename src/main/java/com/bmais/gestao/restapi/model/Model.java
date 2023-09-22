package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.restmodel.BaseRestModel;

import java.io.Serializable;

public abstract class Model<R extends BaseRestModel> extends BaseModel implements Serializable
{
    public abstract R modelParaRest();
}
