package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.AvaliacaoRelatorioMapper;
import com.bmais.gestao.restapi.restmodel.RestAvaliacaoRelatorio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "RELATORIO")
@Data
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AvaliacaoRelatorio extends Model<RestAvaliacaoRelatorio> implements Serializable, Comparable<AvaliacaoRelatorio> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "REL_ID")
    @Include
    private Long id;

    @Column(name = "REL_DESCRICAO")
    private String descricao;
    
    @ManyToMany(mappedBy = "avaliacaoRelatorio")
    private List<Visita> visitas;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "VIS_ID")
//    private Visita visita;
//
//    @Column(name = "ARE_ANTECEDENTES")
//    private Boolean antecedentes;
//
//    @Column(name = "ARE_INTERNACOES")
//    private Boolean internacoes;
//
//    @Column(name = "ARE_CIRURGIAS")
//    private Boolean cirurgias;
//
//    @Column(name = "ARE_PO_UTI")
//    private Boolean poUti;
//
//    @Column(name = "ARE_TRANSFERENCIAS")
//    private Boolean transferencias;
//
//    @Column(name = "ARE_CONTINUIDADE")
//    private Boolean continuidade;
//
//    @Column(name = "ARE_SIGLAS")
//    private Boolean siglas;
//
//    @Column(name = "ARE_LAUDOS")
//    private Boolean laudos;
//
//    @Column(name = "ARE_GRAVIDADE")
//    private Boolean gravidade;
//
//    @Column(name = "ARE_MOTIVO")
//    private Boolean motivo;
//
//    @Column(name = "ARE_CURATIVOS")
//    private Boolean curativos;
//
//    @Column(name = "ARE_DIETAS")
//    private Boolean dietas;
//
//    @Column(name = "ARE_DROGAS")
//    private Boolean drogas;
//
//    @Column(name = "ARE_EQUIPAMENTOS")
//    private Boolean equipamentos;
//
//    @Column(name = "ARE_GASOTERAPIA")
//    private Boolean gasoterapia;
//
//    @Column(name = "ARE_MEDICAMENTOS")
//    private Boolean medicamentos;
//
//    @Column(name = "ARE_HEMODIALISE")
//    private Boolean hemodialise;
//
//    @Column(name = "ARE_SANGUE")
//    private Boolean sangue;
//
//    @Column(name = "ARE_FISIOTERAPIA")
//    private Boolean fisioterapia;
//
//    @Column(name = "ARE_PROCEDIMENTOS")
//    private Boolean procedimentos;

    @Override
    public RestAvaliacaoRelatorio modelParaRest(){
        return AvaliacaoRelatorioMapper.INSTANCE.convertToRest(this);
    }

	@Override
	public int compareTo(AvaliacaoRelatorio o)
	{
		return this.descricao.compareTo(o.descricao);
	}
}
