package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.HospitalMapper;
import com.bmais.gestao.restapi.model.Hospital;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestHospital extends RestModel<Hospital> implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty(value = "razaoSocial")
    private String razaoSocial;

    @JsonProperty(value = "cnpj")
    private String cnpj;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "auditavel")
    private Boolean auditavel;

    @JsonProperty(value = "status")
    private RestStatusHospital status;

    @JsonProperty(value = "endereco")
    private RestEndereco endereco;

    @JsonProperty(value = "administrativo")
    private Boolean administrativo;

    @JsonProperty(value = "contatos")
    private Collection<RestContato> contatos;

    @JsonProperty(value = "clientes")
    private Collection<RestHospitalCliente> clientes;

    @Override
    public Hospital restParaModel() {
        Hospital hospital = HospitalMapper.INSTANCE.convertToModel(this);
        if(hospital.getContatos() != null){
            hospital.getContatos().forEach(c -> c.setHospital(hospital));
        }
        return hospital;
    }
}
