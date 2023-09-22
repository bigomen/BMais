//package com.bmais.gestao.restapi.model;
//
//import com.bmais.gestao.restapi.mapper.HistoricoCapeanteMapper;
//import com.bmais.gestao.restapi.model.enums.TipoHistorico;
//import com.bmais.gestao.restapi.restmodel.RestHistoricoCapeante;
//import com.bmais.gestao.restapi.utility.UtilData;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "HISTORICO_CAPEANTE")
//@Data
//@DynamicInsert
//@DynamicUpdate
//@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
//public class HistoricoCapeante extends Model<RestHistoricoCapeante> implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HISTORICO_CAPEANTE")
//    @SequenceGenerator(name = "SEQ_HISTORICO_CAPEANTE", sequenceName = "SEQ_HISTORICO_CAPEANTE", allocationSize = 1)
//    @Column(name = "HCA_ID")
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CAP_ID")
//    private Capeante capeante;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USU_ID")
//    private Usuario usuario;
//
//    @Column(name = "HCA_TP_HISTORICO")
//    @Enumerated(EnumType.STRING)
//    private TipoHistorico tipoHistorico;
//
//    @Column(name = "HCA_DATA")
//    private LocalDateTime data;
//
//    @PrePersist
//    private void prePersist()
//    {
//        this.data = UtilData.obterDataHoraAtual();
//    }
//
//    public HistoricoCapeante(Long idHistoricoCapeante, Long idUsuario, String emailUsuario,
//                             TipoHistorico tipoHistorico, LocalDateTime data) {
//        this.setId(idHistoricoCapeante);
//        Usuario usuario = new Usuario();
//        usuario.setId(idUsuario);
//        usuario.setEmail(emailUsuario);
//        this.usuario = usuario;
//        this.tipoHistorico = tipoHistorico;
//        this.data = data;
//    }
//
//    @Override
//    public RestHistoricoCapeante modelParaRest() {
//        return HistoricoCapeanteMapper.INSTANCE.convertToRest(this);
//    }
//}
