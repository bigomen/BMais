package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.UsuarioMapper;
import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestUsuario extends RestModel<Usuario> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "status")
    private RestStatusUsuario status;

    @JsonProperty(value = "grupo")
    private RestGrupoUsuario grupo;

    @JsonProperty(value = "empresas")
    private Collection<RestEmpresa> empresas;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "data_inicio")
    private LocalDate dataInicio;

    @JsonProperty(value = "data_fim")
    private LocalDate dataFim;

    @Override
    public Usuario restParaModel() {
        return UsuarioMapper.INSTANCE.convertToModel(this);
    }
}
