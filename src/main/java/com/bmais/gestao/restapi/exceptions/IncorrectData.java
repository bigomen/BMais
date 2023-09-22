package com.bmais.gestao.restapi.exceptions;

public class IncorrectData extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IncorrectData(String msg){
        super(msg);
    }
}
