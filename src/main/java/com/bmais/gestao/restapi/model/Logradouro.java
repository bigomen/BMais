package com.bmais.gestao.restapi.model;


import com.bmais.gestao.restapi.mapper.LogradouroMapper;
import com.bmais.gestao.restapi.restmodel.RestLogradouro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "LOGRADOURO")
@Data
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Logradouro extends Model<RestLogradouro> implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LOG_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "log_tipo")
    private String tipo;

    @Column(name = "log_nome")
    private String nome;

    @Column(name = "log_bairro")
    private String bairro;

    @Column(name = "log_cidade")
    private String cidade;

    @Column(name = "log_uf")
    private String uf;

    @Override
    public RestLogradouro modelParaRest(){return LogradouroMapper.INSTANCE.convertToRest(this);}
}
