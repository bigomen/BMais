package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import com.bmais.gestao.restapi.mapper.ProntuarioMotivoAltaMapper;
import com.bmais.gestao.restapi.restmodel.RestProntuarioMotivoAlta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRONTUARIO_MOTIVO_ALTA")
@Immutable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = false)
public class ProntuarioMotivoAlta extends Model<RestProntuarioMotivoAlta> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "PMA_ID")
    private Long id;

    @Column(name = "PMA_DESCRICAO")
    private String descricao;

    public ProntuarioMotivoAlta(Long id)
    {
        this.id = id;
    }

    @Override
    public RestProntuarioMotivoAlta modelParaRest(){
        return ProntuarioMotivoAltaMapper.INSTANCE.convertToRest(this);
    }
}
