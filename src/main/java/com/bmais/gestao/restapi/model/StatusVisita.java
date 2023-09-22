package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.StatusVisitaMapper;
import com.bmais.gestao.restapi.restmodel.RestStatusVisita;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS_VISITA")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class StatusVisita extends Model<RestStatusVisita> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final Long APROVADO = 2L;
    public static final Long RASCUNHO = 1L;
    public static final Long PENDENTE = 3L;
    public static final Long RECUSADO = 4L;
    public static final Long EXCLUIDO = 5L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "SVI_ID")
    private Long id;

    @Column(name = "SVI_DESCRICAO")
    private String descricao;

    public StatusVisita(String descricao){
        this.setDescricao(descricao);
    }

    public StatusVisita(Long id)
    {
        this.id = id;
    }

    @Override
    public RestStatusVisita modelParaRest(){
        return StatusVisitaMapper.INSTANCE.convertToRest(this);
    }
}
