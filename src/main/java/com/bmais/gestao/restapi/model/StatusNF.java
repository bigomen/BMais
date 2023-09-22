package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusNFMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusNF;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_NOTA_FISCAL")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class StatusNF extends Model<RestStatusNF> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "SNF_ID", nullable = false)
    private Long id;

    @Column(name = "SNF_DESCRICAO")
    private String descricao;

    @Override
    public RestStatusNF modelParaRest(){
        return StatusNFMapper.INSTANCE.modelParaRest(this);
    }
}
