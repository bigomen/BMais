package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.HospitalCliente;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(config = PessoaJuridicaMapper.class, uses = {UsuarioMapper.class, ClienteServicoMapper.class, HospitalClienteMapper.class},
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @InheritConfiguration(name = "convertToFullPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "hospitais", qualifiedByName = "toRestHospitalCliente"),
            @Mapping(target = "servicos", qualifiedByName = "toRestServicoCliente"),
            @Mapping(target = "logotipo", ignore = true)
    })
    RestCliente convertToFullCliente(Cliente cliente);

    @InheritConfiguration(name = "convertToSimplesPessoaJuridica")
    @Mappings(value = {
        @Mapping(target = "logotipo", ignore = true),
        @Mapping(target = "hospitais", ignore = true)
    })
    RestCliente convertToRest(Cliente cliente);

    @Named(value = "convertToSimplesCliente")
    @InheritConfiguration(name = "convertToSimplesPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "cnpjDiferente", ignore = true),
            @Mapping(target = "cnpjFinanceiro", ignore = true),
            @Mapping(target = "capeante", ignore = true),
            @Mapping(target = "valoraltoCusto", ignore = true),
            @Mapping(target = "sigla", ignore = true),
            @Mapping(target = "usuarios", ignore = true),
            @Mapping(target = "hospitais", ignore = true),
            @Mapping(target = "dhProcedimentoCapeante", ignore = true),
            @Mapping(target = "percProcedimentoCapeante", ignore = true),
            @Mapping(target = "dayClinicCapeante", ignore = true),
            @Mapping(target = "complementoCapeante", ignore = true),
            @Mapping(target = "pacoteCapeante", ignore = true),
            @Mapping(target = "abrirDiariasCapeante", ignore = true),
            @Mapping(target = "prorrogacao", ignore = true),
            @Mapping(target = "servicos", ignore = true),
            @Mapping(target = "logotipo", ignore = true)
    })
    RestCliente convertToListaSimples(Cliente cliente);

    @InheritConfiguration(name = "convertToPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "servicos", qualifiedByName = "toClienteServico"),
            @Mapping(target = "hospitais", qualifiedByName = "toHospitalCliente"),
            @Mapping(target = "usuarios", qualifiedByName = "toUsuario"),
            @Mapping(target = "logotipo", ignore = true)
    })
    Cliente convertToModel(RestCliente restCliente);

    @Named(value = "toCliente")
    default Cliente convertToCliente(RestCliente restCliente)
    {
    	if(restCliente == null || StringUtils.isBlank(restCliente.getId()))
    	{
    		return null;
    	}
    	
        return new Cliente(UtilSecurity.decryptId(restCliente.getId()));
    }

    @Named(value = "toRestClienteHospital")
    @InheritConfiguration(name = "convertToSimplesPessoaJuridica")
    @Mappings(value = {
            @Mapping(target = "razaoSocial", source = "cliente.razaoSocial"),
            @Mapping(target = "id", source = "cliente.id"),
            @Mapping(target = "cnpjDiferente", ignore = true),
            @Mapping(target = "cnpjFinanceiro", ignore = true),
            @Mapping(target = "capeante", ignore = true),
            @Mapping(target = "valoraltoCusto", ignore = true),
            @Mapping(target = "sigla", ignore = true),
            @Mapping(target = "usuarios", ignore = true),
            @Mapping(target = "hospitais", ignore = true),
            @Mapping(target = "dhProcedimentoCapeante", ignore = true),
            @Mapping(target = "percProcedimentoCapeante", ignore = true),
            @Mapping(target = "dayClinicCapeante", ignore = true),
            @Mapping(target = "complementoCapeante", ignore = true),
            @Mapping(target = "pacoteCapeante", ignore = true),
            @Mapping(target = "abrirDiariasCapeante", ignore = true),
            @Mapping(target = "prorrogacao", ignore = true),
            @Mapping(target = "servicos", ignore = true)
    })
    RestCliente convertToRestCliente(HospitalCliente hospitalCliente);
}
