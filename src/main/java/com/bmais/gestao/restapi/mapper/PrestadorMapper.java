package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.restmodel.RestPrestador;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(config = PessoaJuridicaMapper.class, uses = {AuditorMapper.class, UtilSecurity.class, EnderecoMapper.class,
        ContatoMapper.class, DadosBancariosMapper.class, DocumentoMapper.class}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface PrestadorMapper
{
    PrestadorMapper INSTANCE = Mappers.getMapper(PrestadorMapper.class);

    @InheritConfiguration(name = "convertToFullPessoaJuridica")
    RestPrestador convertToRest(Prestador fornecedorAuditoria);
    
    @Named(value = "convertToSimplesFornecedor")
    @InheritConfiguration(name = "convertToSimplesPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "auditores", ignore = true),
            @Mapping(target = "pendencias", ignore = true)
    })
    RestPrestador convertToListaSimples(RestPrestador prestador);

    @InheritConfiguration(name = "convertToPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "auditores", ignore = true)
    })
    Prestador convertToModel(RestPrestador restFornecedorAuditoria);
    
    @Named(value = "toPrestador")
    default Prestador convertToCliente(RestPrestador restPrestador)
    {
    	if(restPrestador == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restPrestador.getId()))
    	{
    		return null;
    	}
    	
        return new Prestador(UtilSecurity.decryptId(restPrestador.getId()));
    }
}
