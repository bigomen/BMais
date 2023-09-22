package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.FolhaPagamentoMapper;
import com.bmais.gestao.restapi.model.FolhaPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RestFolhaPagamento extends RestModel<FolhaPagamento> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "empresa")
    private RestEmpresa empresa;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "data_emissao")
    private LocalDate dataEmissao;

    @JsonProperty(value = "data_vencimento")
    private LocalDate dataVencimento;

    @JsonProperty(value = "dados_bancarios")
    private RestDadosBancarios dadosBancarios;

    @JsonProperty(value = "usuario")
    private RestUsuario usuario;

    @Override
    public FolhaPagamento restParaModel(){
        return FolhaPagamentoMapper.INSTANCE.convertToModel(this);
    }
}
