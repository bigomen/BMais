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

public abstract class BaseRestModel <E extends BaseModel<?>> {
    
    private String id;
    
	public abstract E restParaModel();
    
    public final void setId(String id) {
        this.id = id;
    }
    
    public final String getId() {
        return this.id;
    }
    
    
    
    
}
