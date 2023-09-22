package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.StatusEmpresaMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusEmpresa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_EMPRESA")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class StatusEmpresa extends Model<RestStatusEmpresa> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final long ATIVO = 1L;
    public static final long INATIVO = 2L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "SEM_ID", nullable = false)
    private Long id;

    @Column(name = "SEM_DESCRICAO")
    private String descricao;

    public StatusEmpresa(Long id){this.id = id;}

    @Override
    public RestStatusEmpresa modelParaRest(){return StatusEmpresaMapper.INSTANCE.convertToRest(this);}
}
