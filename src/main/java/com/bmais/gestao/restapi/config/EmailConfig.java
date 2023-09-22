package com.bmais.gestao.restapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EmailConfig {

    @Value("${email.host}")
    private String host;

    @Value("${email.porta}")
    private Integer porta;

    @Value("${email.usuario}")
    private String usuario;

    @Value("${email.senha}")
    private String senha;

    @Value("${email.propriedades.autenticacao.descricao}")
    private String autenticacao;

    @Value("${email.propriedades.autenticacao.habilitado}")
    private String authHabilitado;

    @Value("${email.propriedades.tls.descricao")
    private String tls;

    @Value("${email.propriedades.tls.habilitado")
    private String tlsHabilitado;

    @Value("${email.autor}")
    private String autor;
}
