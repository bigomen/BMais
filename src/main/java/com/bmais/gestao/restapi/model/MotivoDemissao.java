package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.MotivoDemissaoMapper;
import com.bmais.gestao.restapi.restmodel.RestMotivoDemissao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOTIVO_DEMISSAO")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class MotivoDemissao extends Model<RestMotivoDemissao> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MDE_ID")
    private Long id;

    @Column(name = "MDE_DESCRICAO")
    private String descricao;

    @Override
    public RestMotivoDemissao modelParaRest(){
        return MotivoDemissaoMapper.INSTANCE.convertToRest(this);
    }
}
