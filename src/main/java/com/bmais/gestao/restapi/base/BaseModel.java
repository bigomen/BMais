/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmais.gestao.restapi.base;

/**
 *
 * @author rcerqueira
 */
public abstract class BaseModel <R extends BaseRestModel<?>> {

    public abstract Long getId();
    public abstract void setId(Long id);
    public abstract R modelParaTO();
 
    
}
