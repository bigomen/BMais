package com.bmais.gestao.restapi.utility;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bmais.gestao.restapi.model.enums.CategoriaGrupoUsuario;
import com.bmais.gestao.restapi.security.ControleAcesso;
import com.bmais.gestao.restapi.security.JWTAuthentication;
import com.bmais.gestao.restapi.security.VinculoFiltro;

public class RuleUtil{

    public static void restringeAcesso(BaseRuleUtil params){
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        if(Objects.equals(controleAcesso.getCategoria(), CategoriaGrupoUsuario.AU.toString())){
            params.setVinculos(controleAcesso.getVinculos());
        }else if (Objects.equals(controleAcesso.getCategoria(), CategoriaGrupoUsuario.CL.toString())){
            params.setClienteId(controleAcesso.getClienteId());
        }
    }
    

    public static Long getUsuarioId() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getUsuarioId();
    }

    public static String getUsuarioNome() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getUsuarioNome();
    }

    
    public static String getUsuarioEmail() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getUsuarioEmail();
    }

    public static Long getClienteId() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getClienteId();

    }

    public static Long getGrupoId() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getIdGrupo();

    }

    public static String getCategoriaGrupo() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getCategoria();
    }

    public static List<VinculoFiltro> getVinculos() {
        JWTAuthentication auth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        ControleAcesso controleAcesso = auth.getControleAcesso();
        return controleAcesso.getVinculos();

    }
    
    public static JWTAuthentication getUsuarioLogado()
    {
    	return  (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
