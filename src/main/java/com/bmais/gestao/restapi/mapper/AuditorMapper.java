package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.Cobertura;
import com.bmais.gestao.restapi.model.Prestador;
import com.bmais.gestao.restapi.restmodel.RestAuditor;
import com.bmais.gestao.restapi.restmodel.RestCobertura;
import com.bmais.gestao.restapi.restmodel.RestPrestador;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@Mapper(uses = {UtilSecurity.class, EnderecoMapper.class, TipoAuditorMapper.class,
        StatusAuditorMapper.class, ServicoMapper.class, VinculoAuditorMapper.class, UsuarioMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface AuditorMapper {

    AuditorMapper INSTANCE = Mappers.getMapper(AuditorMapper.class);

    @Named(value = "toRestAuditor")
    @Mappings(value = {
            @Mapping(target = "prestador", ignore = true),
//            @Mapping(target = "usuario.senha", ignore = true),
            @Mapping(target = "assinatura", ignore = true),
            @Mapping(target = "coberturas", ignore = true),
            @Mapping(target = "vinculos", qualifiedByName = "toRestVinculo")
    })
    RestAuditor convertToRest(Auditor auditor);
    
    @Named(value = "toRestAuditorEscala")
    @Mappings(value = {
            @Mapping(target = "prestador", ignore = true),
            @Mapping(target = "usuario", ignore = true),
            @Mapping(target = "assinatura", ignore = true),
            @Mapping(target = "coberturas", ignore = true),
            @Mapping(target = "vinculos", ignore = true),
            @Mapping(target = "documentos", ignore = true),
            @Mapping(target = "endereco", ignore = true),            
            @Mapping(target = "dataNascimento", ignore = true),            
            @Mapping(target = "cpf", ignore = true),            
            @Mapping(target = "rg", ignore = true),            
            @Mapping(target = "emissor", ignore = true),            
            @Mapping(target = "sexo", ignore = true),
            @Mapping(target = "telefone", ignore = true)
    })
    RestAuditor convertToRestEscala(Auditor auditor);
    
    @Named(value = "toAuditorSimples")
    @Mappings(value = {
            @Mapping(target = "prestador", ignore = true),
            @Mapping(target = "usuario", ignore = true),
            @Mapping(target = "assinatura", ignore = true),
            @Mapping(target = "coberturas", ignore = true),
            @Mapping(target = "vinculos", ignore = true),
            @Mapping(target = "documentos", ignore = true),
            @Mapping(target = "endereco", ignore = true),            
            @Mapping(target = "dataNascimento", ignore = true),            
            @Mapping(target = "crmCorem", ignore = true),            
            @Mapping(target = "cpf", ignore = true),            
            @Mapping(target = "rg", ignore = true),            
            @Mapping(target = "emissor", ignore = true),            
            @Mapping(target = "tipoAuditor", ignore = true),            
            @Mapping(target = "sexo", ignore = true), 
            @Mapping(target = "telefone", ignore = true),
            @Mapping(target = "status", ignore = true)         
    })
    RestAuditor convertToSimpleRest(Auditor auditor);

    @Mappings(value = {
            @Mapping(target = "prestador", qualifiedByName = "toPrestador"),
            @Mapping(target = "assinatura", ignore = true),
            @Mapping(target = "coberturas", ignore = true),
            @Mapping(target = "usuario", qualifiedByName = "toUsuario")
//            @Mapping(target = "vinculos", qualifiedByName = "toVinculo")
    })
    Auditor convertToModel(RestAuditor restAuditor);

    @Named(value = "toPrestador")
    default Prestador convertToModel(RestPrestador prestador)
    {
        if(prestador != null){
            return new Prestador(UtilSecurity.decryptId(prestador.getId()));
        }
        return null;
    }

    @Named(value = "convertCobertura")
    default Collection<RestCobertura> convertCobertura(Collection<Cobertura> coberturas){
        if(coberturas != null){
            return coberturas.stream().map(Cobertura::modelParaRest).collect(Collectors.toList());
        }
        return null;
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

    @Named(value = "toAuditorVisita")
    default Collection<Auditor> toAuditorVisita(RestAuditor auditor){
        if(auditor == null || StringUtils.isBlank(auditor.getId())){
            return null;
        }
        Collection<Auditor> auditors = new ArrayList<>();
        auditors.add(auditor.restParaModel());
        return auditors;
    }

    @Named(value = "toAuditorVisitaSimples")
    default RestAuditor toAuditorVisitaSimples(Collection<Auditor> auditors){
        if(auditors == null){
            return null;
        }
        RestAuditor auditor = new RestAuditor();
        auditor.setId(UtilSecurity.encryptId(auditors.stream().findFirst().get().getId()));
        auditor.setNome(auditors.stream().findFirst().get().getNome());
        return auditor;
    }
}
