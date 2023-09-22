package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.restmodel.RestPessoaJuridicaPesquisa;

import java.util.Collection;
import java.util.Optional;

public interface ClienteRepositoryCustom {

    Collection<Cliente> pesquisarCliente(RestPessoaJuridicaPesquisa params);
    Optional<Cliente> pesquisarCliente(Long id);
}
