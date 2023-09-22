package com.bmais.gestao.restapi.restmodel;


import com.bmais.gestao.restapi.mapper.ServicoMapper;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.model.enums.ClientePrestador;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class RestServico extends RestModel<Servico> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "codigo")
    private String codigo;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "clientePrestador")
    private ClientePrestador clientePrestador;

    @JsonProperty(value = "categoriaServicos")
    private Collection<RestCategoriaServico> categoriaServicos;
    
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @JsonProperty(value = "codigoDescricao")
    private String codigoDescricao;

    @JsonProperty(value = "editavel")
    private Boolean editavel;

    @Override
    public Servico restParaModel() {
        Servico servico = ServicoMapper.INSTANCE.convertToModel(this);
        if(servico.getCategorias() != null){
            servico.getCategorias().forEach(s -> s.setServico(servico));
        }
        return servico;
    }

	public String getCodigoDescricao()
	{
		return this.getCodigo() + " - " + this.getDescricao();
	}
}
