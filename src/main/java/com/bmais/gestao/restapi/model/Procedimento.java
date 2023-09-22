package com.bmais.gestao.restapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROCEDIMENTO")
@DynamicInsert
@DynamicUpdate
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Procedimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCEDIMENTO")
    @SequenceGenerator(name = "SEQ_PROCEDIMENTO", sequenceName = "SEQ_PROCEDIMENTO", allocationSize = 1)
    @Column(name = "PRO_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PRO_DESCRICAO")
    private String descricao;

    @Column(name = "PRO_CODIGO")
    private String codigo;
}
