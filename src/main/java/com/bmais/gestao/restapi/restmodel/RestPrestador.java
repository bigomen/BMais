package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.PrestadorMapper;
import com.bmais.gestao.restapi.model.Prestador;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestPrestador extends RestPessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "auditores")
    private Collection<RestAuditor> auditores;
    
    @JsonProperty(value = "pendencias")
    private boolean pendencias;

    @Override
    public Prestador restParaModel()
    {
        return PrestadorMapper.INSTANCE.convertToModel(this);
    }

}
