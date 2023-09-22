package com.bmais.gestao.restapi.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication extends UsernamePasswordAuthenticationToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ControleAcesso controleAcesso;


    public JWTAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, ControleAcesso controleAcesso) 
    {
        super(principal, credentials, authorities);
        this.controleAcesso = controleAcesso;
    }

    public JWTAuthentication(Object principal, Object credentials, ControleAcesso controleAcesso)
    {
        super(principal, credentials);
        this.controleAcesso = controleAcesso;
    }

    public ControleAcesso getControleAcesso() 
    {
        return controleAcesso;
    }
}
