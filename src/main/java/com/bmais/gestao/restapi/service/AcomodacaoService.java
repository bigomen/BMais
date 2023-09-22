package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.Acomodacao;
import com.bmais.gestao.restapi.repository.AcomodacaoRepository;
import com.bmais.gestao.restapi.restmodel.RestAcomodacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class AcomodacaoService extends Service<Acomodacao, RestAcomodacao>
{
    private final AcomodacaoRepository acomodacaoRepository;

    @Autowired
    public AcomodacaoService(AcomodacaoRepository acomodacaoRepository)
    {
        this.acomodacaoRepository = acomodacaoRepository;
    }

    public Collection<RestAcomodacao> listar()
    {
        return super.getAll()
                .stream()
                .sorted((a1, a2) -> a1.getDescricao().compareTo(a2.getDescricao()))
                .collect(Collectors.toList());
    }

    @Override
    protected CrudRepository<Acomodacao, Long> getRepository()
    {
        return acomodacaoRepository;
    }
}
