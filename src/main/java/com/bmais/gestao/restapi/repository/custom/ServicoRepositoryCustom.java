package com.bmais.gestao.restapi.repository.custom;

import com.bmais.gestao.restapi.model.Cliente;
import com.bmais.gestao.restapi.model.PessoaJuridica;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.restmodel.RestServicoPesquisa;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ServicoRepositoryCustom {

    Collection<Servico> listarServicos(RestServicoPesquisa params);

}
