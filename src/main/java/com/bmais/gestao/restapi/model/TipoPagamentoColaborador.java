package com.bmais.gestao.restapi.model;

import com.bmais.gestao.restapi.mapper.TipoPagamentoColaboradorMapper;
import com.bmais.gestao.restapi.restmodel.RestTipoPagamentoColaborador;
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
@Table(name = "TIPO_PAGAMENTO_COLABORADOR")
@Data
@Immutable
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class TipoPagamentoColaborador extends Model<RestTipoPagamentoColaborador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "TPC_ID")
    private Long id;

    @Column(name = "TPC_DESCRICAO")
    private String descricao;

    public TipoPagamentoColaborador(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public RestTipoPagamentoColaborador modelParaRest(){
        return TipoPagamentoColaboradorMapper.INSTANCE.convertToRest(this);
    }
}
