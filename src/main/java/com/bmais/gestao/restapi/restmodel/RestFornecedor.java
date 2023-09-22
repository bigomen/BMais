package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.FornecedorMapper;
import com.bmais.gestao.restapi.model.Fornecedor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestFornecedor extends RestPessoaJuridica
{
    @JsonProperty(value =  "ramo_atividade")
    private RestRamoAtividade ramoAtividade;

    @Override
    public Fornecedor restParaModel(){
        return FornecedorMapper.INSTANCE.convertToModel(this);
    }
}
