package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.exceptions.UsuarioDesativadoException;
import com.bmais.gestao.restapi.jwt.DetalheUsuarioData;
import com.bmais.gestao.restapi.model.StatusUsuario;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalheUsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.encontrarUsuario(email);
        if(!usuario.isPresent()){
            throw new UsernameNotFoundException("Usuario [" + email + "] não encontrado");
        }
        if(usuario.get().getStatus().getId() != StatusUsuario.ATIVO){
            throw new UsuarioDesativadoException("Usuario [" + email + "] está desativado");
        }
        return new User(usuario.get().getEmail(), usuario.get().getSenha(), getGrantedAuthorities(usuario.get()));
    }

    private Collection<DetalheUsuarioData> getGrantedAuthorities(Usuario usuario) {

        List<DetalheUsuarioData> authorities = new ArrayList<>();

        authorities.add(new DetalheUsuarioData(usuario));
        return authorities;
    }
}
