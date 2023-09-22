package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusFeriasMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusFerias;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_FERIAS")
@Immutable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class StatusFerias extends Model<RestStatusFerias> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Long ATIVO = 1L;
    public static final Long FINALIZADO = 2L;
    public static final Long VENCIDO = 3L;

    @Id
    @Column(name = "SFE_ID")
    private Long id;

    @Column(name = "SFE_DESCRICAO")
    private String descricao;

    public StatusFerias(Long id) {
        this.id = id;
    }

    @Override
    public RestStatusFerias modelParaRest(){
        return StatusFeriasMapper.INSTANCE.convertToRest(this);
    }
}
