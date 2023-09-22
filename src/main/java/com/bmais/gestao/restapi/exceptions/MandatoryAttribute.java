package com.bmais.gestao.restapi.exceptions;

public class MandatoryAttribute extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MandatoryAttribute(String atributo){super(atributo + " é obrigatorio");}
}
