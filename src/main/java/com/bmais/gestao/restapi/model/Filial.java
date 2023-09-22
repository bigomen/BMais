//package com.bmais.gestao.restapi.model;
//
//import com.bmais.gestao.restapi.mapper.FilialMapper;
//import com.bmais.gestao.restapi.restmodel.RestFilial;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "FILIAL")
//@DynamicInsert
//@DynamicUpdate
//@Data
//@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class Filial extends Model<RestFilial> implements Serializable {
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FILIAL")
//    @SequenceGenerator(name = "SEQ_FILIAL", sequenceName = "SEQ_FILIAL", allocationSize = 1)
//    @EqualsAndHashCode.Include
//    @Column(name = "FIL_ID")
//    private Long id;
//
//    @Column(name = "FIL_RAZAO_SOCIAL")
//    private String razaoSocial;
//
//    @Column(name = "FIL_CNPJ")
//    private String cnpj;
//
//    @Column(name = "FIL_IE")
//    private String ie;
//
//    @Column(name = "FIL_IM")
//    private String im;
//
//    @Column(name = "FIL_EMAIL")
//    private String email;
//
//    @Column(name = "FIL_TELEFONE")
//    private String telefone;
//
//    @Column(name = "FIL_COR")
//    private String cor;
//
//    @Column(name = "FIL_DT_INICIO")
//    protected LocalDateTime dataInicio;
//
//    @Column(name = "FIL_DT_FIM")
//    protected LocalDateTime dataFim;
//
//    @AttributeOverrides(value = {
//            @AttributeOverride(name = "cep", column = @Column(name = "FIL_CEP")),
//            @AttributeOverride(name = "logradouro", column = @Column(name = "FIL_LOGRADOURO")),
//            @AttributeOverride(name = "numero", column = @Column(name = "FIL_NUMERO")),
//            @AttributeOverride(name = "complemento", column = @Column(name = "FIL_COMPLEMENTO")),
//            @AttributeOverride(name = "bairro", column = @Column(name = "FIL_BAIRRO"))
//    })
//    @AssociationOverride(name = "municipio", joinColumns = @JoinColumn(name = "MUN_ID"))
//    private Endereco endereco;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "EMP_ID")
//    private Empresa empresa;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "SFI_ID")
//    private StatusFilial status;
//
//    @PrePersist
//    private void preInsert()
//    {
//        this.status = new StatusFilial(StatusFilial.ATIVO);
//    }
//
//    @Override
//    public RestFilial modelParaRest() {
//        return FilialMapper.INSTANCE.convertToRest(this);
//    }
//}
