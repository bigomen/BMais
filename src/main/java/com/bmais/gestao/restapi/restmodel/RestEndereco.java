package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.EnderecoMapper;
import com.bmais.gestao.restapi.model.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RestEndereco extends RestModel<Endereco> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Obrigatório informar o logradouro")
    @Size(max = 128, message = "Deve ter no máximo 128 caracteres")
    @JsonProperty(value = "logradouro")
    private String logradouro;

    @Size(max = 125, message = "Complemento deve ter no máximo 125 caracteres")
    @JsonProperty(value = "complemento")
    private String complemento;

    @NotBlank(message = "Obrigatório informar o logradouro")
    @Size(max = 64, message = "Deve ter no máximo 64 caracteres")
    @JsonProperty(value = "bairro")
    private String bairro;

    @NotBlank(message = "Obrigatório informar o logradouro")
    @Size(max = 5, message = "Deve ter no máximo 5 caracteres")
    @JsonProperty(value = "numero")
    private String numero;

    @NotBlank(message = "Obrigatório informar o cep")
    @Size(max = 8, message = "Deve ter no máximo 8 caracteres")
    @JsonProperty(value = "cep")
    private String cep;

    @NotNull(message = "Obrigatório informar o município")
    @JsonProperty(value = "municipio")
    private RestMunicipio municipio;

    public Endereco restParaModel()
    {
        return EnderecoMapper.convertToModel(this);
    }
}
