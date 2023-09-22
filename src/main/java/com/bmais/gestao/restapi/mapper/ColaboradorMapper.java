package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Colaborador;
import com.bmais.gestao.restapi.restmodel.RestColaborador;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, DependenteMapper.class, DocumentoMapper.class, ClienteMapper.class, MotivoDemissaoMapper.class,
		ValeTransporteMapper.class, EnderecoMapper.class, AfastamentoMapper.class, ColaboradorBeneficioMapper.class, FeriasMapper.class, CipaMapper.class, EvolucaoColaboradorMapper.class})
public interface ColaboradorMapper {

    ColaboradorMapper INSTANCE = Mappers.getMapper(ColaboradorMapper.class);
    @Mappings(value = {
		@Mapping(target = "dadosBancarios", ignore = true),
    	@Mapping(target = "cliente", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "convertToSimplesCliente")
    })
    RestColaborador convertToRest(Colaborador colaborador);

    @Mappings(value = {
		@Mapping(target = "dadosBancarios", ignore = true),
		@Mapping(target = "cliente", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, qualifiedByName = "toCliente")
    		
    })
    Colaborador convertToModel(RestColaborador restColaborador);
    
    @Named(value = "toColaboradorSimples")
    default RestColaborador convertToSimplerest(Colaborador colaborador)
    {
    	if(colaborador == null)
    	{
    		return null;
    	}
    	
    	RestColaborador rest = new RestColaborador();
    	rest.setId(UtilSecurity.encryptId(colaborador.getId()));
    	rest.setNome(colaborador.getNome());
		return rest;
    }
    
    @Named(value = "toColaborador")
    default Colaborador toColaborador(RestColaborador restColaborador)
    {
    	if(restColaborador == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restColaborador.getId()))
    	{
    		return null;
    	}
    	
        return new Colaborador(UtilSecurity.decryptId(restColaborador.getId()));
    }
}
