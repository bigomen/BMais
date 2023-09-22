package com.bmais.gestao.restapi.exceptions;

public class ObjectAlreadyInBase extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectAlreadyInBase(String msg){
        super(msg);
    }
}
