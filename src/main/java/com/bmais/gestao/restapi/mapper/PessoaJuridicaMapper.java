package com.bmais.gestao.restapi.mapper;

import com.bmais.gestao.restapi.model.PessoaJuridica;
import com.bmais.gestao.restapi.restmodel.RestPessoaJuridica;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.mapstruct.*;

@MapperConfig(uses = {UtilSecurity.class, EnderecoMapper.class, ContatoMapper.class, DadosBancariosMapper.class, DocumentoMapper.class, StatusPessoaJuridicaMapper.class})
public interface PessoaJuridicaMapper
{
    @Mappings(value = {
            @Mapping(target = "status", ignore = true)
    })
    PessoaJuridica convertToPessoaJuridica(RestPessoaJuridica pessoaJuridica);


    @Mappings(value = {
            @Mapping(target = "ie", ignore = true),
            @Mapping(target = "im", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "telefone", ignore = true),
            @Mapping(target = "dataInicio", ignore = true),
            @Mapping(target = "dataFim", ignore = true),
            @Mapping(target = "endereco", ignore = true),
            @Mapping(target = "documentos", ignore = true),
            @Mapping(target = "contatos", ignore = true),
            @Mapping(target = "dadosBancarios", ignore = true),
            @Mapping(target = "ir", ignore = true),
            @Mapping(target = "pis", ignore = true),
            @Mapping(target = "cofins", ignore = true),
            @Mapping(target = "csll", ignore = true),
            @Mapping(target = "gps", ignore = true),
            @Mapping(target = "iss", ignore = true),
            @Mapping(target = "outrosImpostos", ignore = true)
    })
    RestPessoaJuridica convertToSimplesPessoaJuridica(PessoaJuridica pessoaJuridica);

    @Mappings(value = {
            @Mapping(target = "status", source = "status")
    })
    RestPessoaJuridica convertToFullPessoaJuridica(PessoaJuridica pessoaJuridica);
}
