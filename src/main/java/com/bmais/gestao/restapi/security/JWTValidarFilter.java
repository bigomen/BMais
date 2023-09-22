package com.bmais.gestao.restapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.bmais.gestao.restapi.security.JWTAutenticarFilter.*;

public class JWTValidarFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";
    public static final String HEADER_REFRESH = "Refresh";

    public JWTValidarFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader(HEADER_ATRIBUTO);
        if(atributo == null){
            chain.doFilter(request, response);
            return;
        }
        if(!atributo.startsWith(ATRIBUTO_PREFIXO)){
            chain.doFilter(request, response);
            return;
        }
        String token = atributo.replace(ATRIBUTO_PREFIXO, "");
        JWTAuthentication authenticationToken = null;
        if(isRefreshToken(request) && JWT.decode(token).getClaim("refresh").asBoolean()){
            authenticationToken = getRefreshAutheticationToken(token, response);
        }else if(!JWT.decode(token).getClaim("refresh").asBoolean()) {
            authenticationToken = getAutheticationToken(token);
        }
        if(authenticationToken != null){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }else {
            if(isRefreshToken(request)){
                response.setStatus(403);
                response.flushBuffer();
            }else if(!isPublic(request)){
                response.setStatus(401);
                response.flushBuffer();
            }
            else {
                chain.doFilter(request,response);
            }
        }
    }

    private boolean isRefreshToken(HttpServletRequest request) {
        return request.getRequestURI().contains("/refresh");
    }

    private boolean isPublic(HttpServletRequest request) {
        return request.getRequestURI().indexOf("/login") == 0
                || request.getRequestURI().indexOf("/esqueceuSenha/v1/**") == 0
                || request.getRequestURI().indexOf("//swagger-ui/**") == 0
                || request.getRequestURI().indexOf("/swagger-resources/**") == 0
                || request.getRequestURI().indexOf("/swagger-ui.html") == 0
                || request.getRequestURI().indexOf("/v3/api-docs/**") == 0
                || request.getRequestURI().indexOf("/webjars/**") == 0;
    }

    private JWTAuthentication getAutheticationToken(String token){
        try {
            String usuario = JWT.require(Algorithm.HMAC512(TOKEN_SENHA))
                    .build().verify(token)
                    .getSubject();
            if(usuario == null){
                return null;
            }
            Map<String, Object> map = JWT.decode(token).getClaim("controleAcesso").asMap();
            ControleAcesso controleAcesso = new ControleAcesso(map);
            return new JWTAuthentication(usuario, null, new ArrayList<>(), controleAcesso);

        }catch (ExpiredJwtException e){
            return null;
        }
    }

    private JWTAuthentication getRefreshAutheticationToken(String token, HttpServletResponse response){
        try {
            String usuario = JWT.require(Algorithm.HMAC512(TOKEN_SENHA))
                    .build().verify(token)
                    .getSubject();
            if(usuario == null){
                return null;
            }
            Map<String, Object> map = JWT.decode(token).getClaim("controleAcesso").asMap();
            ControleAcesso controleAcesso = new ControleAcesso(map);
            token = JWT.create()
                    .withClaim("controleAcesso" , controleAcesso.toMap())
                    .withClaim("refresh", false)
                    .withSubject(usuario)
                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                    .sign(Algorithm.HMAC512(TOKEN_SENHA));
            String refresh_token = JWT.create()
                    .withClaim("controleAcesso" , controleAcesso.toMap())
                    .withClaim("refresh", true)
                    .withSubject(usuario)
                    .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRACAO))
                    .sign(Algorithm.HMAC512(TOKEN_SENHA));
            response.addHeader(HEADER_REFRESH, refresh_token);
            response.addHeader(HEADER_ATRIBUTO, token);
            return new JWTAuthentication(usuario, null, new ArrayList<>(), controleAcesso);

        }catch (ExpiredJwtException e){
            return null;
        }
    }
}
