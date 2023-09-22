package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ImpostosMapper;
import com.bmais.gestao.restapi.restmodel.RestImpostos;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "IMPOSTO")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Impostos extends Model<RestImpostos> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "IMP_ID", nullable = false)
    private Long id;

    @Column(name = "IMP_DESCRICAO")
    private String descricao;

    @Column(name = "IMP_VALOR")
    private BigDecimal valor;

    @Override
    public RestImpostos modelParaRest(){
        return ImpostosMapper.INSTANCE.modelParaRest(this);
    }
}
