package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Vinculo;
import com.bmais.gestao.restapi.model.projections.EscalaProjection;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import com.bmais.gestao.restapi.restmodel.RestEscala;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, AuditorMapper.class, ServicoMapper.class, CoberturaMapper.class})
public interface EscalaMapper
{
	EscalaMapper INSTANCE = Mappers.getMapper(EscalaMapper.class);
	
	@Named(value = "toRestEscala")
	@Mappings(value = {
			@Mapping(target = "id", source = "idEscala"),
			@Mapping(target = "cobertura", qualifiedByName = "toRestCoberturaEscala"),
			@Mapping(target = "auditor", qualifiedByName = "toRestAuditorEscala"),
			@Mapping(target = "cliente", source = "cliente", qualifiedByName = "toCliente"),
			@Mapping(target = "hospital", qualifiedByName = "toHospital"),
			@Mapping(target = "servico", qualifiedByName = "toRestServico")
	})
	RestEscala convertToRest(EscalaProjection escala);
	
	default Vinculo convertToEscala(RestEscala restEscala)
	{
		return new Vinculo(UtilSecurity.decryptId(restEscala.getId()));
	}
	
	@Named(value = "toCliente")		
	default RestCliente toCliente (String cliente)
	{
		RestCliente restCliente = new RestCliente();
		restCliente.setRazaoSocial(cliente);
		return restCliente;
	}
	
	@Named(value = "toHospital")		
	default RestHospital toHospital (String hospital)
	{
		RestHospital restHospital = new RestHospital();
		restHospital.setRazaoSocial(hospital);
		return restHospital;
	}
}
