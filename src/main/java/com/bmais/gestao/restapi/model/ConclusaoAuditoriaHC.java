package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.ConclusaoAuditoriaHCMapper;
import com.bmais.gestao.restapi.restmodel.RestConclusaoAuditoriaHC;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "CONCLUSAO_AUDITORIA_VHC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
public class ConclusaoAuditoriaHC extends Model<RestConclusaoAuditoriaHC> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "CAV_ID")
    private Long id;

    @Column(name = "CAV_DESCRICAO")
    private String descricao;

    @ManyToMany(mappedBy = "conclusoes", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<AuditoriaVisitaHomeCare> auditorias;

    public ConclusaoAuditoriaHC(Long id, String descricao){
        this.setId(id);
        this.setDescricao(descricao);
    }

    @Override
    public RestConclusaoAuditoriaHC modelParaRest(){
        return ConclusaoAuditoriaHCMapper.INSTANCE.convertToRest(this);
    }
}
