package com.bmais.gestao.restapi.jwt;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.security.ControleAcesso;

public class DetalheUsuarioData implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
	private String username;
    private ControleAcesso controleAcesso = null;
    private Long id;
    private String nome;

    public DetalheUsuarioData(Usuario u) {
            this.username = u.getEmail();
            this.id = u.getId();
            this.nome = u.getNome();
            this.controleAcesso = new ControleAcesso(u);
    }

    public DetalheUsuarioData(String username, Long id, String nome, ControleAcesso controleAcesso){
        this.username = username;
        this.id = id;
        this.nome = nome;
        this.controleAcesso = controleAcesso;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<ControleAcesso> c = new ArrayList<>();
        c.add(controleAcesso);
        return c;
    }

    public ControleAcesso getControleAcesso() {
        return controleAcesso;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
