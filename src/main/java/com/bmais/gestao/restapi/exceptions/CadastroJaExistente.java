package com.bmais.gestao.restapi.exceptions;

public class CadastroJaExistente extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CadastroJaExistente(String msg){super(msg);}
}
