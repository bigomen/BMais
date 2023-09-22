package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.restmodel.RestClienteServico;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "SERVICO_CLIENTE_CATEGORIA")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClienteServico extends Model<RestClienteServico> implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICO_CLIENTE_CATEGORIA")
    @SequenceGenerator(name = "SEQ_SERVICO_CLIENTE_CATEGORIA", sequenceName = "SEQ_SERVICO_CLIENTE_CATEGORIA", allocationSize = 1)
    @Column(name = "SCC_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEJ_ID")
    @ToString.Exclude
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SER_ID")
    @ToString.Exclude
    private Servico servico;

    @Column(name = "SCC_DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "SCC_DATA_FIM")
    private LocalDate dataFim;

    @Column(name = "SCC_VALOR")
    private BigDecimal valor;

    @Override
    public RestClienteServico modelParaRest()
    {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClienteServico that = (ClienteServico) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
