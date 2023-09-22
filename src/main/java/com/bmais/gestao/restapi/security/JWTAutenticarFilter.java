package com.bmais.gestao.restapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bmais.gestao.restapi.exceptions.UsuarioDesativadoException;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRACAO = 1200000_00;

    public static final int REFRESH_TOKEN_EXPIRACAO = 2400000_00;

    public static final String TOKEN_SENHA = "dc30dd97-5630-4de2-ac4a-d8c883398061";

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
        	JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        	
            Usuario usuario = new Usuario();
            usuario.setEmail(data.get("email").getAsString());
            usuario.setSenha(data.get("senha").getAsString());
            
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),
                    usuario.getSenha(),
                    new ArrayList<>()

            ));
        }catch (ExpiredJwtException e){
            throw  new RuntimeException("Sessão expirou", e);
        }
        catch (Exception e) {
            if(e.getCause() instanceof UsuarioDesativadoException){
                throw new UsuarioDesativadoException(e.getLocalizedMessage());
            }
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        @SuppressWarnings("unchecked")
		Optional<DetalheUsuarioData> usuarioData = (Optional<DetalheUsuarioData>) authResult.getAuthorities().stream().findFirst();

        
        if (usuarioData.isPresent()) {
        	String token = JWT.create()
                .withClaim("controleAcesso" , usuarioData.get().getControleAcesso().toMap())
                .withClaim("refresh", false)
                .withSubject(usuarioData.get().getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

            String refresh_token = JWT.create()
                    .withClaim("controleAcesso" , usuarioData.get().getControleAcesso().toMap())
                    .withClaim("refresh", true)
                    .withSubject(usuarioData.get().getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRACAO))
                    .sign(Algorithm.HMAC512(TOKEN_SENHA));

            HashMap<String, String> payload = new HashMap<>();
            payload.put("token", token);
            payload.put("refresh_token", refresh_token);
            payload.put("usuario_id", UtilSecurity.encryptId(usuarioData.get().getId()));
            payload.put("usuario_nome", usuarioData.get().getNome());
            payload.put("usuario_email",usuarioData.get().getUsername());
            payload.put("grupo", usuarioData.get().getGrupo().toString());
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(payload));

        }
        response.getWriter().flush();
        

    }
}
