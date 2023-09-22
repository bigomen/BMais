package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.DependenteMapper;
import com.bmais.gestao.restapi.restmodel.RestDependente;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "DEPENDENTE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Dependente extends Model<RestDependente> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEPENDENTE")
    @SequenceGenerator(name = "SEQ_DEPENDENTE", sequenceName = "SEQ_DEPENDENTE", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "DEP_ID")
    private Long id;

    @Column(name = "DEP_NOME")
    private String nome;

    @Column(name = "DEP_NASCIMENTO")
    private LocalDate nascimento;

    @Column(name = "DEP_RG")
    private String rg;

    @Column(name = "DEP_CPF")
    private String cpf;

    @Column(name = "DEP_SUS")
    private String sus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COL_ID")
    private Colaborador colaborador;

    @OneToMany(mappedBy = "dependente" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ColaboradorBeneficio> colaboradorBeneficios;

    public Dependente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Dependente(Long id, String nome, LocalDate nascimento, String rg, String cpf, String sus) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.sus = sus;
    }

    @Override
    public RestDependente modelParaRest() {
        return DependenteMapper.INSTANCE.convertToRest(this);
    }
}
