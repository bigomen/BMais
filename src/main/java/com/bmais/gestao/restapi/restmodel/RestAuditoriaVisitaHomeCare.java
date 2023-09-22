package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.AuditoriaVisitaHomeCareMapper;
import com.bmais.gestao.restapi.model.AuditoriaVisitaHomeCare;
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
public class RestAuditoriaVisitaHomeCare extends RestModel<AuditoriaVisitaHomeCare> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "auditor")
    private RestAuditor auditor;

    @JsonProperty(value = "data_auditoria")
    private LocalDate dataAuditoria;

    @JsonProperty(value = "data_ultima_internacao")
    private LocalDate dataUltimaInternacao;

    @JsonProperty(value = "ultima_visita")
    private LocalDate dataUltimavisita;

    @JsonProperty(value = "complemento")
    private String complemento;

    @JsonProperty(value = "informante")
    private String informante;

    @JsonProperty(value = "historico_clinico_geral")
    private String historicoClinicoGeral;

    @JsonProperty(value = "evolucao_estado_geral")
    private String evolucaoEstadoGeral;

    @JsonProperty(value = "dados_vitais_pa")
    private String dadosVitaisPa;

    @JsonProperty(value = "temperatura")
    private String temperatura;

    @JsonProperty(value = "estado_clinico_atual")
    private String estadoClinicoGeral;

    @JsonProperty(value = "nivel_consciencia")
    private String nivelConciencia;

    @JsonProperty(value = "mobilidade")
    private String mobilidade;

    @JsonProperty(value = "sng")
    private Boolean sng;

    @JsonProperty(value = "sne")
    private Boolean sne;

    @JsonProperty(value = "sva")
    private Boolean sva;

    @JsonProperty(value = "xdia")
    private Integer xdia;

    @JsonProperty(value = "svd")
    private Boolean svd;

    @JsonProperty(value = "uripen")
    private Boolean uripen;

    @JsonProperty(value = "concentrador")
    private Boolean concentrador;

    @JsonProperty(value = "concentrador_continuo")
    private Boolean concentradorContinuo;

    @JsonProperty(value = "concentrador_hdia")
    private Integer concentradorHdia;

    @JsonProperty(value = "bipap")
    private Boolean bipap;

    @JsonProperty(value = "bipap_hdia")
    private Integer bipapHdia;

    @JsonProperty(value = "cpap")
    private Boolean cpap;

    @JsonProperty(value = "cpap_hdia")
    private Integer cpapHdia;

    @JsonProperty(value = "monitor")
    private Boolean monitor;

    @JsonProperty(value = "respirador")
    private Boolean respirador;

    @JsonProperty(value = "respirador_descricao")
    private String respiradorDescricao;

    @JsonProperty(value = "continuo")
    private Boolean continuo;

    @JsonProperty(value = "intermitente")
    private Boolean intermitente;

    @JsonProperty(value = "intermitente_hdia")
    private Integer intermitenteHdia;

    @JsonProperty(value = "troca_torpedo")
    private Boolean trocaTorpedo;

    @JsonProperty(value = "troca_torpedo_quantidade")
    private Integer trocaTorpedoQuant;

    @JsonProperty(value = "troca_torpedo_periodo")
    private String trocaTorpedoPeriodo;

    @JsonProperty(value = "existe_cuidador")
    private Boolean existeCuidador;

    @JsonProperty(value = "nome_cuidador")
    private String nomeCuidador;

    @JsonProperty(value = "habilitado_cuidar")
    private Boolean habilitadoCuidar;

    @JsonProperty(value = "cuidador_recebe_instrucao")
    private Boolean cuidadorRecebeInstrucao;

    @JsonProperty(value = "cuidador_condicoes_aprender")
    private Boolean cuidadorCondicoesAprender;

    @JsonProperty(value = "cuidador_delega")
    private Boolean cuidadorDelega;

    @JsonProperty(value = "serv_satisfatorio")
    private Boolean servSatisfatorio;

    @JsonProperty(value = "pq_serv_nao_satisfatorio")
    private String servNaoSatisfatorio;

    @JsonProperty(value = "observacao")
    private String observacao;

    @JsonProperty(value = "sintomas")
    private Collection<RestSintomaQuadroClinico> sintomas;

    @JsonProperty(value = "diagnosticos")
    private Collection<RestDiagnosticoAuditoriaVisitaHC> diagnosticos;

    @JsonProperty(value = "feridas")
    private Collection<RestFeridasAuditoriaVisitaHC> feridas;

    @JsonProperty(value = "stomias")
    private Collection<RestStomiaAuditoriaVisitaHC> stomias;

    @JsonProperty(value = "servicos_prestados")
    private Collection<RestServicoPrestadoAuditoriaVisitaHC> servicosPrestados;

    @JsonProperty(value = "materiais")
    private Collection<RestMaterialAuditoriaVisitaHC> materiais;

    @JsonProperty(value = "medicamentos")
    private Collection<RestMedicamentoAuditoriaVisitaHC> medicamentos;

    @JsonProperty(value = "equipamentos")
    private Collection<RestEquipamentoAuditoriaVisitaHC> equipamentos;

    @JsonProperty(value = "avaliacao")
    private RestAvaliacaoDependenciaVisitaHC avaliacao;

    @JsonProperty(value = "conclusoes")
    private Collection<RestConclusaoAuditoriaHC> conclusoes;

    @Override
    public AuditoriaVisitaHomeCare restParaModel(){
        return AuditoriaVisitaHomeCareMapper.INSTANCE.convertToModel(this);
    }
}
