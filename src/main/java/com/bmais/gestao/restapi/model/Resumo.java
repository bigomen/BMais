package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ResumoMapper;
import com.bmais.gestao.restapi.restmodel.RestResumoCapeanteInternacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESUMO_CAPEANTE")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class Resumo extends Model<RestResumoCapeanteInternacao> implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESUMO_CAPEANTE")
	@SequenceGenerator(name = "SEQ_RESUMO_CAPEANTE", sequenceName = "SEQ_RESUMO_CAPEANTE", allocationSize = 1)
	@Column(name = "REC_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "REC_DH_INICIO")
	private LocalDateTime dataHoraInicio;
	
	@Column(name = "REC_DH_FIM")
	private LocalDateTime dataHoraFim;
	
	@Column(name = "REC_PORCENTAGEM_PROCEDIMENTO")
	private Integer percentualProcedimento = 0;
	
	@Column(name = "REC_APARTAMENTO_DIARIA_COBRADA")
	private BigDecimal apdiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_APARTAMENTO_DIARIA_GLOSADA")
	private BigDecimal apDiariaGlosada = BigDecimal.ZERO;
	
	@Column(name = "REC_APARTAMENTO_VALOR_APRESENTADO")
	private BigDecimal apValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_APARTAMENTO_VALOR_GLOSADO")
	private BigDecimal apValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_ENFERMARIA_DIARIA_COBRADA")
	private BigDecimal enfDiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_ENFERMARIA_DIARIA_GLOSADA")
	private BigDecimal enfDiariaGlosada = BigDecimal.ZERO;
	
	@Column(name = "REC_ENFERMARIA_VALOR_APRESENTADO")
	private BigDecimal enfValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_ENFERMARIA_VALOR_GLOSADO")
	private BigDecimal enfValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_SEMI_DIARIA_COBRADA")
	private BigDecimal semiDiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_SEMI_DIARIA_GLOSADA")
	private BigDecimal semiDiariaglosada = BigDecimal.ZERO;
	
	@Column(name = "REC_SEMI_VALOR_APRESENTADO")
	private BigDecimal semiValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_SEMI_VALOR_GLOSADO")
	private BigDecimal semiValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_UTI_DIARIA_COBRADA")
	private BigDecimal utiDiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_UTI_DIARIA_GLOSADA")
	private BigDecimal utiDiariaGlosada = BigDecimal.ZERO;
	
	@Column(name = "REC_UTI_VALOR_APRESENTADO")
	private BigDecimal utiValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_UTI_VALOR_GLOSADO")
	private BigDecimal utiValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_BERCARIO_DIARIA_COBRADA")
	private BigDecimal bercarioDiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_BERCARIO_DIARIA_GLOSADA")
	private BigDecimal bercarioDiariaGlosada = BigDecimal.ZERO;
	
	@Column(name = "REC_BERCARIO_VALOR_APRESENTADO")
	private BigDecimal bercarioValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_BERCARIO_VALOR_GLOSADO")
	private BigDecimal bercarioValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_DAYCLINIC_DIARIA_COBRADA")
	private BigDecimal dayClinicDiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_DAYCLINIC_DIARIA_GLOSADA")
	private BigDecimal dayClinicDiariaGlosada = BigDecimal.ZERO;
	
	@Column(name = "REC_DAYCLINIC_VALOR_APRESENTADO")
	private BigDecimal dayClinicValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_DAYCLINIC_VALOR_GLOSADO")
	private BigDecimal dayClinicValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_ACOMPANHANTE_DIARIA_COBRADA")
	private BigDecimal acompanhanteDiariaCobrada = BigDecimal.ZERO;
	
	@Column(name = "REC_ACOMPANHANTE_DIARIA_GLOSADA")
	private BigDecimal acompanhanteDiariaGlosada = BigDecimal.ZERO;
	
	@Column(name = "REC_ACOMPANHANTE_VALOR_APRESENTADO")
	private BigDecimal acompanhanteValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_ACOMPANHANTE_VALOR_GLOSADO")
	private BigDecimal acompanhanteValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_DIARIA_VALOR_APRESENTADO")
	private BigDecimal diariasValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_DIARIA_VALOR_GLOSADO")
	private BigDecimal diariasValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_TAXAS_VALOR_APRESENTADO")
	private BigDecimal taxasValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_TAXAS_VALOR_GLOSADO")
	private BigDecimal taxasValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_GASES_VALOR_APRESENTADO")
	private BigDecimal gasesValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_GASES_VALOR_GLOSADO")
	private BigDecimal gasesValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_HONORARIO_VALOR_APRESENTADO")
	private BigDecimal honorariosValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_HONORARIO_VALOR_GLOSADO")
	private BigDecimal honorariosValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_SADT_VALOR_APRESENTADO")
	private BigDecimal sadtValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_SADT_VALOR_GLOSADO")
	private BigDecimal sadtValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_HEMODERIVADOS_VALOR_APRESENTADO")
	private BigDecimal hemoderivadosValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_HEMODERIVADOS_VALOR_GLOSADO")
	private BigDecimal hemoderivadosValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_MATERIAIS_VALOR_APRESENTADO")
	private BigDecimal materiaisValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_MATERIAIS_VALOR_GLOSADO")
	private BigDecimal materiaisValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_MAT_ESP_VALOR_APRESENTADO")
	private BigDecimal matEspecialValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_MAT_ESP_VALOR_GLOSADO")
	private BigDecimal matEspecialValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_MEDICAMENTOS_VALOR_APRESENTADO")
	private BigDecimal medicamentosValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_MEDICAMENTOS_VALOR_GLOSADO")
	private BigDecimal medicamentosValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_PACOTE_VALOR_APRESENTADO")
	private BigDecimal pacoteValorApresentado = BigDecimal.ZERO;
	
	@Column(name = "REC_PACOTE_VALOR_GLOSADO")
	private BigDecimal pacoteValorGlosado = BigDecimal.ZERO;
	
	@Column(name = "REC_DESCONTO")
	private BigDecimal desconto = BigDecimal.ZERO;
	
	@Column(name = "REC_ISOLAMENTO")
	private Boolean isolamento = Boolean.FALSE;
	
	@Column(name = "REC_OBSERVACAO_DG")
	private String observacaoDG;
	
	@Column(name = "REC_DATA_FECHAMENTO_CONTA")
	private LocalDate dataFechamentoConta;
	
	@Column(name = "REC_OBSERVACAO")
	private String observacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUS_ID")
	private Tuss tuss;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAP_ID")
	private CapeanteInternacao capeante;

	@Override
	public RestResumoCapeanteInternacao modelParaRest(){
		return ResumoMapper.INSTANCE.convertToRest(this);
	}
	
	public BigDecimal valorTotalApresentado()
	{
		return this.diariasValorApresentado
				.add(this.taxasValorApresentado)
				.add(this.gasesValorApresentado)
				.add(this.honorariosValorApresentado)
				.add(this.sadtValorApresentado)
				.add(this.hemoderivadosValorApresentado)
				.add(this.materiaisValorApresentado)
				.add(this.matEspecialValorApresentado)
				.add(this.medicamentosValorApresentado)
				.add(this.pacoteValorApresentado);
	}
	
	public BigDecimal valorTotalGlosado()
	{
		return this.diariasValorGlosado
				.add(this.taxasValorGlosado)
				.add(this.gasesValorGlosado)
				.add(this.honorariosValorGlosado)
				.add(this.sadtValorGlosado)
				.add(this.hemoderivadosValorGlosado)
				.add(this.materiaisValorGlosado)
				.add(this.matEspecialValorGlosado)
				.add(this.medicamentosValorGlosado)
				.add(this.pacoteValorGlosado);
				
	}
	
	public BigDecimal valorTotalLiberado()
	{
		return this.valorTotalGlosado().subtract(this.desconto);
	}
}
