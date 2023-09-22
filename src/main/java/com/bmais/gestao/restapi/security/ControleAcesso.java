package com.bmais.gestao.restapi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.model.Vinculo;

public class ControleAcesso implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

	@Override
    public String getAuthority() {
        return null;
    }

    Map<String, Object> controleAcesso = new HashMap<>();

    public ControleAcesso(Usuario u) {
    	Long clienteId = null;
    	if (u.getCliente() != null && u.getCliente().getId() != null)
    		clienteId = u.getCliente().getId();
    	
    	GrupoUsuario grupoUsuario = null;
    	if (u.getGrupo() != null && u.getGrupo().getId() != null)
    		grupoUsuario = u.getGrupo();
    	
    	
    	// Propriedade do usuario (1:1)
        this.controleAcesso.put("clienteId", clienteId);
        this.controleAcesso.put("usuarioId", u.getId());

        
        this.controleAcesso.put("usuarioNome", u.getNome());
        this.controleAcesso.put("usuarioEmail", u.getEmail());

        if(u.getAuditor() != null)
        	this.controleAcesso.put("auditorId", u.getAuditor().getId());
        else
        	this.controleAcesso.put("auditorId", null);
        
        
        // Grupo
        HashMap<String, Object> grupo = new HashMap<>();
        this.controleAcesso.put("grupo", grupo);
        grupo.put("id", grupoUsuario.getId());
        grupo.put("categoria", grupoUsuario.getCategoria().toString());
        
        
        // Vinculo
        List<HashMap<String, Object>> vinculosAR = new ArrayList<>();
        if(u.getAuditor() != null && u.getAuditor().getVinculos() != null){
        	Collection<Vinculo> vinculos = u.getAuditor().getVinculos();
            vinculos.forEach(c -> {
                HashMap<String, Object> vinculo = new HashMap<>();
                vinculo.put("cliente",c.getCliente().getId());
                vinculo.put("hospital", c.getHospital().getId());
                vinculo.put("servico", c.getServico().getId());
                vinculosAR.add(vinculo);
            });
        }
        this.controleAcesso.put("vinculos", vinculosAR);
    }

    public ControleAcesso(Map<String, Object> controleAcesso){
        this.controleAcesso = controleAcesso;
    }

    @SuppressWarnings("unchecked")
	public Long getIdGrupo(){
        return getLong(((HashMap<String, Object>) this.controleAcesso.get("grupo")).get("id"));
    }

    @SuppressWarnings("unchecked")
	public String getCategoria(){
        return (String) ((HashMap<String, Object>) this.controleAcesso.get("grupo")).get("categoria");
    }

    public Long getClienteId(){
        return getLong(this.controleAcesso.get("clienteId"));
    }

    public Long getAuditorId(){
        return getLong(this.controleAcesso.get("auditorId"));
    }

    
    public Long getUsuarioId(){
        return getLong(this.controleAcesso.get("usuarioId"));
    }

    public String getUsuarioNome(){
        return (String)(this.controleAcesso.get("usuarioNome"));
    }

    public String getUsuarioEmail(){
        return (String)(this.controleAcesso.get("usuarioEmail"));
    }

    
    public Long getLong(Object o){
        if(o == null){
            return null;
        }
        if(o instanceof Integer){
            return ((Integer) o).longValue();
        }else if (o instanceof Long){
            return (Long) o;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	public List<VinculoFiltro> getVinculos(){
        List<VinculoFiltro> vinculoFiltros = new ArrayList<>();
        for (HashMap<String, Object> h : (List<HashMap<String, Object>>) this.controleAcesso.get("vinculos")){
            VinculoFiltro vinculoFiltro = new VinculoFiltro();
            vinculoFiltro.setHospitalId(getLong(h.get("hospital")));
            vinculoFiltro.setClienteId(getLong(h.get("cliente")));
            vinculoFiltro.setServicoId(getLong(h.get("servico")));
            vinculoFiltros.add(vinculoFiltro);
        }
        return vinculoFiltros;
    }

    public Map<String, Object> toMap() {
        return controleAcesso;
    }
}
