package com.bmais.gestao.restapi.restmodel;

import lombok.EqualsAndHashCode;

public abstract class BaseRestModel {

    @EqualsAndHashCode.Include
    private String id;

    public final void setId(String id) {
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }
}
