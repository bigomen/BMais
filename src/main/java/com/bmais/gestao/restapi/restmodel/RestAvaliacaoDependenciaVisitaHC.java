package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import com.bmais.gestao.restapi.mapper.AvaliacaoDependenciaVisitaHCMapper;
import com.bmais.gestao.restapi.model.AvaliacaoDependenciaVisitaHC;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestAvaliacaoDependenciaVisitaHC extends RestModel<AvaliacaoDependenciaVisitaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "sondas")
    private String sonda;

    @JsonProperty(value = "stomias")
    private String stomia;

    @JsonProperty(value = "acesso_venoso")
    private String acessoVenoso;

    @JsonProperty(value = "adm_medicamento")
    private String admMedicamento;

    @JsonProperty(value = "aparelhagem")
    private String aparelhagem;

    @JsonProperty(value = "curativo")
    private String curativo;

    @JsonProperty(value = "higiene_pessoal")
    private String higienePessoal;

    @JsonProperty(value = "dieta")
    private String dieta;

    @JsonProperty(value = "mobilizacao")
    private String mobilizacao;

    @JsonProperty(value = "vigilancia_pessoal")
    private String vigilanciaPessoal;

    @JsonProperty(value = "sondas_cuidador")
    private Boolean sondaCuidador;

    @JsonProperty(value = "stomia_cuidador")
    private Boolean stomiaCuidador;

    @JsonProperty(value = "acesso_venoso_cuidador")
    private Boolean acessoVenosoCuidador;

    @JsonProperty(value = "adm_medicamento_cuidador")
    private Boolean admMedicamentoCuidador;

    @JsonProperty(value = "aparelhagem_cuidador")
    private Boolean aparelhagemCuidador;

    @JsonProperty(value = "curativo_cuidador")
    private Boolean curativoCuidador;

    @JsonProperty(value = "higiene_pessoal_cuidador")
    private Boolean higienePessoalCuidador;

    @JsonProperty(value = "dieta_cuidador")
    private Boolean dietaCuidador;

    @JsonProperty(value = "mobilizacao_cuidador")
    private Boolean mobilizacaoCuidador;

    @JsonProperty(value = "vigilancia_pessoal_cuidador")
    private Boolean vigilanciaPessoalCuidador;

    public AvaliacaoDependenciaVisitaHC restParaModel(){
        return AvaliacaoDependenciaVisitaHCMapper.INSTANCE.convertToModel(this);
    }
}
