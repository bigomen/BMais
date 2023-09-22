/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import com.bmais.gestao.restapi.base.BaseRestModel;

/**
 *
 * @author rcerqueira
 */
public abstract class BaseModel
{
	public abstract Long getId();
	public abstract void setId(Long id);
}
