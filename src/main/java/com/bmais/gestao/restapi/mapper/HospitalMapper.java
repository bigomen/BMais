package com.bmais.gestao.restapi.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, EnderecoMapper.class, ContatoMapper.class, HospitalClienteMapper.class, StatusHospitalMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface HospitalMapper {

    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    @Named(value = "toHospital")
    @Mappings(value = {
            @Mapping(target = "clientes", qualifiedByName = "toRestClienteHospital")
    })
    RestHospital convertToRest(Hospital hospital);

    @Named(value = "toHospitalSimples")
    @Mappings(value = {
        @Mapping(target = "clientes", ignore = true),
        @Mapping(target = "contatos", ignore = true),
        @Mapping(target = "status", ignore = true),
        @Mapping(target = "cnpj", ignore = true),
        @Mapping(target = "telefone", ignore = true),
        @Mapping(target = "endereco", ignore = true),
        @Mapping(target = "auditavel", ignore = true),
            @Mapping(target = "administrativo", ignore = true)
    })
    RestHospital convertToSimpleRest(Hospital hospital);

    Hospital convertToModel(RestHospital restHospital);

    @Named(value = "toHospital")
    default Hospital toHospital(RestHospital restHospital)
    {
    	if(restHospital == null || StringUtils.isBlank(restHospital.getId()))
    	{
    		return null;
    	}
    	
        return new Hospital(UtilSecurity.decryptId(restHospital.getId()));
    }
}