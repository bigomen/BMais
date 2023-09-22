package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Embeddable;
import com.bmais.gestao.restapi.mapper.ClienteMapper;
import com.bmais.gestao.restapi.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RestCliente extends RestPessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "cnpj_diferente")
    private Boolean cnpjDiferente;

    @JsonProperty(value = "cnpj_financeiro")
    private String cnpjFinanceiro;

    @JsonProperty(value = "capeante")
    private Boolean capeante;

    @JsonProperty(value = "valor_alto_custo")
    private BigDecimal valoraltoCusto;

    @JsonProperty(value = "sigla")
    private String sigla;

    @JsonProperty(value = "usuarios")
    private Collection<RestUsuario> usuarios;

    @JsonProperty(value = "hospitais")
    private Collection<RestHospitalCliente> hospitais;

    @JsonProperty(value = "dh_procedimento_capeante")
    private Boolean dhProcedimentoCapeante;

    @JsonProperty(value = "perc_procedimento_capeante")
    private Boolean percProcedimentoCapeante;

    @JsonProperty(value = "day_clinic_capeante")
    private Boolean dayClinicCapeante;

    @JsonProperty(value = "complemento_capeante")
    private Boolean complementoCapeante;

    @JsonProperty(value = "pacote_capeante")
    private Boolean pacoteCapeante;

    @JsonProperty(value = "abrir_diarias_capeante")
    private Boolean abrirDiariasCapeante;

    @JsonProperty(value = "prorrogacao")
    private Boolean prorrogacao;
    
	@JsonProperty(value = "administrativo")
	private Boolean administrativo;
	
	@JsonProperty(value = "assinatura_auditores")
	private Boolean assinaturaAuditores;
	
	@JsonProperty(value = "frequencia_visita")
	private Integer frequenciaVisita;

    @JsonProperty(value = "servicos")
    private Collection<RestClienteServico> servicos;

    @JsonProperty(value = "logo")
    private String logotipo;

    public Cliente restParaModel()
    {
        Cliente cliente = ClienteMapper.INSTANCE.convertToModel(this);
        if(cliente.getUsuarios() != null){
            cliente.getUsuarios().forEach(u -> u.setCliente(cliente));
        }
        return cliente;
    }
}
