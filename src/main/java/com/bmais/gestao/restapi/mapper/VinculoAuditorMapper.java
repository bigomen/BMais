package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Vinculo;
import com.bmais.gestao.restapi.restmodel.RestAuditor;
import com.bmais.gestao.restapi.restmodel.RestEscala;
import com.bmais.gestao.restapi.restmodel.RestVinculo;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {HospitalMapper.class, ServicoMapper.class, CategoriaServicoMapper.class,
        UtilSecurity.class, ClienteMapper.class})
public interface VinculoAuditorMapper
{
    VinculoAuditorMapper INSTANCE = Mappers.getMapper(VinculoAuditorMapper.class);

    @Named(value = "toRestVinculo")
    @Mappings(value = {
            @Mapping(target = "hospital", qualifiedByName = "toHospitalSimples"),
            @Mapping(target = "servico", qualifiedByName = "toRestServico"),
            @Mapping(target = "cliente", qualifiedByName = "convertToSimplesCliente"),
            @Mapping(target = "auditor", qualifiedByName = "convertToSimplesAuditor")
    })
    RestVinculo convertToRest(Vinculo vinculoAuditor);

    @Named(value = "convertToSimplesAuditor")
    default RestAuditor convertToSimplesAuditor(Auditor auditor){
        RestAuditor restAuditor = new RestAuditor();
        restAuditor.setId(UtilSecurity.encryptId(auditor.getId()));
        restAuditor.setNome(auditor.getNome());
        return restAuditor;
    }

    @Named(value = "toRestAgendaVinculo")
    @Mappings(value = {
            @Mapping(target = "hospital", qualifiedByName = "toHospitalSimples"),
            @Mapping(target = "servico", qualifiedByName = "toRestServico"),
            @Mapping(target = "cliente", qualifiedByName = "convertToSimplesCliente"),
            @Mapping(target = "auditor", qualifiedByName = "toAuditorSimples")
    })
    RestVinculo convertToAgendaRest(Vinculo vinculoAuditor);
    
    @Named(value = "toAuditorVinculoRest")
    @Mappings(value = {
            @Mapping(target = "hospital", ignore = true),
            @Mapping(target = "servico", ignore = true),
            @Mapping(target = "categoriaServico", ignore = true),
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "auditor", qualifiedByName = "toAuditorSimples")
    })
    RestVinculo convertToAuditorVinculoRest(Vinculo vinculoAuditor);

    @Mappings(value = {
            @Mapping(target = "hospital", qualifiedByName = "toHospital"),
            @Mapping(target = "servico", qualifiedByName = "toServico"),
            @Mapping(target = "cliente", qualifiedByName = "toCliente"),
            @Mapping(target = "auditor", qualifiedByName = "toAuditor")
    })
    Vinculo convertToModel(RestVinculo restVinculoAuditor);
    
	@Named(value = "toVinculoCobertura")
	default Vinculo toVinculo (RestEscala escala)
	{
		return new Vinculo(UtilSecurity.decryptId(escala.getId()));
	}
	
	@Named(value = "toVinculo")
	default Vinculo toVinculo (RestVinculo vinculo)
	{
		if(vinculo == null)
		{
			return null;
		}
		
		if(StringUtils.isBlank(vinculo.getId()))
		{
			return null;
		}
		
		return new Vinculo(UtilSecurity.decryptId(vinculo.getId()));
	}
	
	@Named(value = "toAuditorSimples")
	default RestAuditor convertToSimpleRestAuditor(Auditor auditor)
	{
        if ( auditor == null ) 
        {
            return null;
        }

        RestAuditor restAuditor = new RestAuditor();

        restAuditor.setId( UtilSecurity.encryptId( auditor.getId() ) );
        restAuditor.setNome( auditor.getNome() );
        restAuditor.setEmail( auditor.getEmail() );

        return restAuditor;
	}
	
    @Named(value = "toAuditor")
    default Auditor toAuditor(RestAuditor auditor)
    {
    	if(auditor == null || StringUtils.isBlank(auditor.getId()))
    	{
    		return null;
    	}
    	
        return new Auditor(UtilSecurity.decryptId(auditor.getId()));
    }
}
