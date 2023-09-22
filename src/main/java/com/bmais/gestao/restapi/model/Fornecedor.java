package com.bmais.gestao.restapi.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.bmais.gestao.restapi.mapper.FornecedorMapper;
import com.bmais.gestao.restapi.restmodel.RestFornecedor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@DiscriminatorValue(value = "2")
public class Fornecedor extends PessoaJuridica<RestFornecedor>
{
    public static final String PASTA_DOCUMENTOS = "FORNECEDORES";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RAT_ID")
    private RamoAtividade ramoAtividade;

    public Fornecedor(Long id){
        this.setId(id);
    }

    public Fornecedor(Long idFornecedor, String razaoSocial, String cnpj, RamoAtividade ramoAtividade, StatusPessoaJuridica status){
        this.setId(idFornecedor);
        this.setRazaoSocial(razaoSocial);
        this.setCnpj(cnpj);
        this.setRamoAtividade(ramoAtividade);
        this.setStatus(status);
    }

    @Override
    public RestFornecedor modelParaRest(){return FornecedorMapper.INSTANCE.convertToRest(this);}
}
