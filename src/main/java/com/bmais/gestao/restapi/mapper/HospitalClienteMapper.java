package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.model.HospitalCliente;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import com.bmais.gestao.restapi.restmodel.RestHospitalCliente;
import com.bmais.gestao.restapi.restmodel.RestServico;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UtilSecurity.class, ServicoMapper.class})
public interface HospitalClienteMapper
{
    HospitalClienteMapper INSTANCE = Mappers.getMapper(HospitalClienteMapper.class);

    @Named(value = "toClienteHospital")
    @Mappings(value = {
            @Mapping(target = "hospital", ignore = true),
            @Mapping(target = "cliente", nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS, qualifiedByName = "toCliente"),
            @Mapping(target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
            @Mapping(target = "codigoCliente", source = "codigoCliente"),
            @Mapping(target = "dg", source = "dg")
    })
    HospitalCliente toClienteHospital(RestHospitalCliente hospital);

    @Named(value = "toHospitalCliente")
    @Mappings(value = {
            @Mapping(target = "hospital", nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS, qualifiedByName = "toHospital"),
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "id", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS),
            @Mapping(target = "codigoCliente", source = "codigoCliente"),
            @Mapping(target = "dg", source = "dg"),
//            @Mapping(target = "servico", qualifiedByName = "toServico")
    })
    HospitalCliente toHospitalCliente(RestHospitalCliente hospital);

    @Named(value = "toRestHospitalCliente")
    @Mappings(value = {
            @Mapping(target = "hospital", qualifiedByName = "toRestHospital"),
            @Mapping(target = "cliente", ignore = true)
    })
    RestHospitalCliente convertToRestHospital (HospitalCliente hospitalCliente);

    @Named(value = "toRestClienteHospital")
    @Mappings(value = {
            @Mapping(target = "cliente", qualifiedByName = "toRestCliente"),
            @Mapping(target = "hospital", ignore = true)
    })
    RestHospitalCliente convertToRestCliente(HospitalCliente hospitalCliente);

    @Named(value = "toHospital")
    default Hospital toHospital(RestHospital restHospital)
    {
        return new Hospital(UtilSecurity.decryptId(restHospital.getId()));
    }

    @Named(value = "toCliente")
    default Cliente toCliente(RestCliente restCliente)
    {
        return new Cliente(UtilSecurity.decryptId(restCliente.getId()));
    }

    @Named(value = "toRestCliente")
    default RestCliente toRestCliente(Cliente cliente)
    {
        RestCliente rc = new RestCliente();
        rc.setId(UtilSecurity.encryptId(cliente.getId()));
        rc.setRazaoSocial(cliente.getRazaoSocial());
        return rc;
    }

    @Named(value = "toRestHospital")
    default RestHospital toRestHospital(Hospital hospital)
    {
       RestHospital rh = new RestHospital();
       rh.setId(UtilSecurity.encryptId(hospital.getId()));
       rh.setRazaoSocial(hospital.getRazaoSocial());
       return rh;
    }
    
    @Named(value = "toModelHospitalCliente")
    default HospitalCliente toModelHospitalCliente(RestHospitalCliente restHospitalCliente)
    {
    	if(restHospitalCliente == null)
    	{
    		return null;
    	}
    	
    	if(StringUtils.isBlank(restHospitalCliente.getId()))
    	{
    		return null;
    	}
    	
    	return new HospitalCliente(UtilSecurity.decryptId(restHospitalCliente.getId()));
    }
    
    /**
     * Método para converter um objeto HospitalCliente em RestHospitalCliente
     * @param hospitalcliente
     * @return
     */
    @Named(value = "toResponseHospitalCliente")
    @Mappings(value = {
    		@Mapping(target = "cliente", qualifiedByName = "toRestCliente"),
    		@Mapping(target = "hospital", qualifiedByName = "toRestHospital")
    })
    RestHospitalCliente toRestHospitalCliente(HospitalCliente hospitalcliente);

    @Named(value = "toServico")
    default Servico toServico(RestServico servico)
    {
        return new Servico(UtilSecurity.decryptId(servico.getId()));
    }
    
    
}
