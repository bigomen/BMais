package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.mapper.VisitaMapper;
import com.bmais.gestao.restapi.model.Visita;
import com.bmais.gestao.restapi.model.enums.TipoInternacao;
import com.bmais.gestao.restapi.model.enums.TipoTratamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestVisita extends RestModel<Visita> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "internacao")
    private RestInternacao internacao;
    
    @JsonProperty(value = "status")
    private RestStatusVisita status;

    //SOLICITAÇÃO DE AUDITORIA
    @JsonProperty(value = "data")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate data;


    @JsonProperty(value = "observacao")
    private String observacao;

    //DADOS CLÍNICOS DO PACIENTE
    @JsonProperty(value = "auditor")
    private RestAuditor auditor;

    @JsonProperty(value = "tipo_internacao")
    private TipoInternacao tipoInternacao;

    @JsonProperty(value = "tipo_tratamento")
    private TipoTratamento tipoTratamento;

    @JsonProperty(value = "isolamento")
    private Boolean isolamento;

    @JsonProperty(value = "acomodacoes")
    private Collection<RestAcomodacaoVisita> acomodacoes;

    @JsonProperty(value = "dataInicioIsolamento")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicioIsolamento;

    @JsonProperty(value = "dataFimIsolamento")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFimIsolamento;

    @JsonProperty(value = "apartamento")
    private Boolean apartamento;

    @JsonProperty(value = "dataInicioApartamento")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicioApartamento;

    @JsonProperty(value = "dataFimApartamento")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFimApartamento;

    @JsonProperty(value = "enfermaria")
    private Boolean enfermaria;

    @JsonProperty(value = "dataInicioEnfermaria")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicioEnfermaria;

    @JsonProperty(value = "dataFimEnfermaria")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFimEnfermaria;

    @JsonProperty(value = "semi")
    private Boolean semi;

    @JsonProperty(value = "dataInicioSemi")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicioSemi;

    @JsonProperty(value = "dataFimSemi")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFimSemi;

    @JsonProperty(value = "uti")
    private Boolean uti;

    @JsonProperty(value = "dataInicioUti")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicioUti;

    @JsonProperty(value = "dataFimUti")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFimUti;

    @JsonProperty(value = "bercario")
    private Boolean bercario;

    @JsonProperty(value = "dataInicioBercario")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataInicioBercario;

    @JsonProperty(value = "dataFimBercario")
    @JsonFormat(pattern = Constantes.PATTERN_DATA_HORA)
    private LocalDateTime dataFimBercario;

    @JsonProperty(value = "cid_principal")
    private RestCID cidPrincipal;

    @JsonProperty(value = "cid_secundario")
    private RestCID cidSecundario;

    @JsonProperty(value = "evolucao")
    private String evolucao;

    @JsonProperty(value = "condicoes_sociais")
    private String condicoesSociais;

    //COMPONENTES DE ALTO CUSTO
    @JsonProperty(value = "componentes_alto_custo")
    private Collection<RestComponenteAltoCusto> componentesAltoCusto;

    //NEGOCIAÇÃO DIRETA
    @JsonProperty(value = "glosa_diaria_quantidade")
    private Long glosaDiariaQuantidade;
    
    @JsonProperty(value = "glosa_diaria_acomodacao")
    private RestAcomodacao glosaDiariaAcomodacao;

    @JsonProperty(value = "troca_diaria_quantidade_negociada")
    private Long trocaDiariaQuantidadeNegociada;

    @JsonProperty(value = "troca_diaria_de")
	private RestAcomodacao trocaDiariaDe;
	
    @JsonProperty(value = "troca_diaria_para")
	private RestAcomodacao trocaDiariaPara;
	
    @JsonProperty(value = "mediacao_negada_quantidade")
    private Long medicacaoNegadaQuantidade;

    @JsonProperty(value = "medicacao_negada_descricao")
    private String medicacaoNegadaDescricao;
    
    @JsonProperty(value = "qtd_procedimento_negado")
    private Integer qtdProcedimentoNegado;

    @JsonProperty(value = "procedimento_negado")
    private RestTuss procedimentoNegado;

    @JsonProperty(value = "troca_procedimento_de")
    private RestTuss trocaProcedimentoDe;

    @JsonProperty(value = "troca_procedimento_para")
    private RestTuss trocaProcedimentoPara;

    //HOME CARE
    @JsonProperty(value = "indicacao_hc")
    private Boolean indicacaoHC;

    @JsonProperty(value = "curativos")
    private Collection<RestCurativo> curativos;

    @JsonProperty(value = "oxigenioterapia_intermitente")
    private boolean oxigenioterapiaIntermitente;

    @JsonProperty(value = "oxigenioterapia_continuo")
    private boolean oxigenioterapiaContinuo;

    @JsonProperty(value = "oxigenioterapia_ventilacao")
    private boolean oxigenioterapiaVentilacao;

    @JsonProperty(value = "mobilizacao")
    private String mobilizacao;

    @JsonProperty(value = "nivel_conciencia")
    private String nivelConciencia;

    @JsonProperty(value = "alimentacao_oral")
    private Boolean alimentacaoOral;

    @JsonProperty(value = "alimentacao_sonda")
    private Boolean alimentacaoSonda;

    @JsonProperty(value = "alimentacao_parenteral")
    private Boolean alimentacaoParenteral;

    @JsonProperty(value = "alimentacao_ostomia")
    private Boolean alimentacaoOstomia;

    @JsonProperty(value = "ostomia_gastromia")
    private Boolean ostomiaGastromia;

    @JsonProperty(value = "ostomia_colostomia")
    private Boolean ostomiaColostomia;

    @JsonProperty(value = "ostomia_jejunostomia")
    private Boolean ostomiaJejunostomia;

    @JsonProperty(value = "acesso_venoso")
    private String acessoVenoso;

    @JsonProperty(value = "traquesostomia")
    private String traquesostomia;

    @JsonProperty(value = "aspiracao_frequencia")
    private Long aspiracaoFrequencia;

    //PRORROGACAO
    @JsonProperty(value = "prorrogacao_ate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prorrogacaoAte;

    @JsonProperty(value = "obsProrrogacao")
    private String obsProrrogacao;

    @JsonProperty(value = "prorrogacao")
    private Boolean prorrogacao;

    //AVALIAÇÃO DE RELATÓRIO
    @JsonProperty(value = "avaliacao_relatorio")
    private Collection<RestAvaliacaoRelatorio> avaliacaoRelatorio;

    @Override
    public Visita restParaModel(){
        Visita visita = VisitaMapper.INSTANCE.convertToModel(this);
        if(visita.getComponentesAltoCusto() == null){
            visita.setComponentesAltoCusto(new ArrayList<>());
        }
        if(visita.getCurativos() == null){
            visita.setCurativos(new ArrayList<>());
        }
        if(visita.getAcomodacoes() != null){
            visita.getAcomodacoes().forEach(a -> a.setVisita(visita));
        }
//        visita.getAvaliacaoRelatorio().setVisita(visita);
        return visita;
    }
}
