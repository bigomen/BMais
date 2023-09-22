package com.bmais.gestao.restapi.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.PrestadorMapper;
import com.bmais.gestao.restapi.restmodel.RestPrestador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@DiscriminatorValue(value = "3")
public class Prestador extends PessoaJuridica<RestPrestador> implements Serializable
{
    public static final String PASTA_DOCUMENTOS = "PRESTADORES";

    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prestador")
    private Collection<Auditor> auditores;
    
    @Column(name = "PEJ_PENDENCIAS")
    private boolean pendencias;

    public Prestador(Long id){
        this.setId(id);
    }

    public Prestador(Long id, String razaoSocial)
    {
        this.setId(id);
        this.setRazaoSocial(razaoSocial);
    }

    @Override
    public RestPrestador modelParaRest(){return PrestadorMapper.INSTANCE.convertToRest(this);}

    public Prestador(Long idPrestador, String razaoSocial, String cnpj, String descStatus)
    {
        this.setId(idPrestador);
        this.setRazaoSocial(razaoSocial);
        this.setCnpj(cnpj);
        this.setStatus(new StatusPessoaJuridica(descStatus));
    }
}
