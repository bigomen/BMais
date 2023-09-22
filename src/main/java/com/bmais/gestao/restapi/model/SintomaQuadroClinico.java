package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import com.bmais.gestao.restapi.mapper.SintomaQuadroClinicoMapper;
import com.bmais.gestao.restapi.restmodel.RestSintomaQuadroClinico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SINTOMA_QUADRO_CLINICO_VHC")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class SintomaQuadroClinico extends Model<RestSintomaQuadroClinico> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SQC_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "SQC_DESCRICAO")
    private String descricao;
    
    @ManyToMany(mappedBy = "sintomas")
    private Collection<AuditoriaVisitaHomeCare> auditorias;

    public SintomaQuadroClinico(Long id, String descricao){
        this.setId(id);
        this.setDescricao(descricao);
    }

    @Override
    public RestSintomaQuadroClinico modelParaRest(){
        return SintomaQuadroClinicoMapper.INSTANCE.convertToRest(this);
    }
}
