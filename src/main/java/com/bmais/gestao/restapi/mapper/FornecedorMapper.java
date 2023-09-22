package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Fornecedor;
import com.bmais.gestao.restapi.restmodel.RestFornecedor;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(config = PessoaJuridicaMapper.class, uses = {UtilSecurity.class, RamoAtividadeMapper.class, EnderecoMapper.class,
        ContatoMapper.class, DadosBancariosMapper.class, DocumentoMapper.class}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface FornecedorMapper
{
    FornecedorMapper INSTANCE = Mappers.getMapper(FornecedorMapper.class);

    @InheritConfiguration(name = "convertToFullPessoaJuridica")
    RestFornecedor convertToRest(Fornecedor fornecedorServicoProduto);
    
    @Named(value = "convertToSimplesFornecedor")
    @InheritConfiguration(name = "convertToSimplesPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "ramoAtividade", ignore = true)
    })
    RestFornecedor convertToListaSimples(Fornecedor fornecedor);

    @InheritConfiguration(name = "convertToPessoaJuridica")
    Fornecedor convertToModel(RestFornecedor restFornecedor);
    
    @Named(value = "toFornecedor")
    default Fornecedor convertToCliente(RestFornecedor restFornecedor)
    {
    	if(restFornecedor == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restFornecedor.getId()))
    	{
    		return null;
    	}
    	
        return new Fornecedor(UtilSecurity.decryptId(restFornecedor.getId()));
    }
}