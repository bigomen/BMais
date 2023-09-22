package com.bmais.gestao.restapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.MovimentacaoBancaria;
import com.bmais.gestao.restapi.restmodel.RestMovimentacaoBancaria;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, DadosBancariosMapper.class, StatusMovimentacaoBancariaMapper.class, PlanoContasMapper.class, TipoMovimentacaoBancariaMapper.class})
public interface MovimentacaoBancariaMapper {

    MovimentacaoBancariaMapper INSTANCE = Mappers.getMapper(MovimentacaoBancariaMapper.class);

    @Mappings(value = {
    		@Mapping(target = "usuario", source = "usuario.nome"),
        	@Mapping(target = "contaOrigem", ignore = true),
        	@Mapping(target = "contaDestino",ignore = true)
    })
    RestMovimentacaoBancaria convertToRest(MovimentacaoBancaria movimentacaoBancaria);

    @Mappings({
    	@Mapping(target = "contaOrigem", qualifiedByName = "toDadosBancarios"),
    	@Mapping(target = "contaDestino", qualifiedByName = "toDadosBancarios"),
    	@Mapping(target = "usuario", ignore = true)
    	
    })
    MovimentacaoBancaria convertToModel(RestMovimentacaoBancaria restMovimentacaoBancaria);
}
