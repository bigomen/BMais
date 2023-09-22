package com.bmais.gestao.restapi.restmodel;


import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import com.bmais.gestao.restapi.mapper.ContatoMapper;
import com.bmais.gestao.restapi.model.Contato;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestContato extends RestModel<Contato> implements Serializable, Comparable<RestContato> {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "celular")
    private String celular;

    @JsonProperty(value = "cpf")
    private String cpf;

    @JsonProperty(value = "cargo")
    private String cargo;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "parentesco")
    private String parentesco;
    
    @JsonProperty(value = "recebeEmail")
	private Boolean recebeEmail;

    @JsonProperty(value = "endereco")
    private RestEndereco endereco;

    @JsonProperty(value = "financeiro")
    private Boolean financeiro;

    @JsonProperty(value = "ramal")
    private String ramal;

    @JsonProperty(value = "observacao")
    private String observacao;

    @Override
    public Contato restParaModel(){return ContatoMapper.INSTANCE.convertToModel(this);}

    @Override
    public int compareTo(RestContato restContato) {
        return StringUtils.compareIgnoreCase(this.cpf, restContato.getCpf());
    }
}
