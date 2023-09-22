package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.CadastroJaExistente;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.CategoriaServico;
import com.bmais.gestao.restapi.model.Servico;
import com.bmais.gestao.restapi.model.StatusServico;
import com.bmais.gestao.restapi.repository.ServicoRepository;
import com.bmais.gestao.restapi.restmodel.RestCategoriaServico;
import com.bmais.gestao.restapi.restmodel.RestServico;
import com.bmais.gestao.restapi.restmodel.RestServicoPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicoService {

    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Collection<RestServico> listar(RestServicoPesquisa params){
        Collection<Servico> servicos = servicoRepository.listarServicos(params);
        return servicos.stream().map(Servico::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestServico> listaSimples(){
        Collection<Servico> servicos = servicoRepository.listaSimples(StatusServico.ATIVO);
        return servicos.stream().map(Servico::modelParaRest).collect(Collectors.toList());
    }

    public RestServico detalhes(String id){
        Optional<Servico> servico = servicoRepository.findById(UtilSecurity.decryptId(id));
        if(servico.isEmpty()){
            throw new ObjectNotFoundException("Serviço não encontrado");
        }
        return servico.get().modelParaRest();
    }

    public Collection<RestServico> pesquisarServicosVinculos(String cliente, String hospital) {
        Set<Servico> servicos = servicoRepository.pesquisarServicosVinculos(UtilSecurity.decryptId(cliente), UtilSecurity.decryptId(hospital));
        return servicos.stream()
                .map(Servico::modelParaRest)
                .collect(Collectors.toList());
    }

    public void novo(RestServico restServico){
        if(servicoRepository.existsByCodigoAndClientePrestador(restServico.getCodigo(), restServico.getClientePrestador()))
        {
            throw new CadastroJaExistente(MensagensID.CodigoCadastrado);
        }
        Servico servico = servicoRepository.save(restServico.restParaModel());
        servico.setEditavel(true);
        servico.modelParaRest();
    }

    @Transactional
    public void atualizar(RestServico restServico, String id){
        Optional<Servico> opServico = servicoRepository.findById(UtilSecurity.decryptId(id));
        if(opServico.isEmpty()){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }

        restServico.setId(id);

        if(UtilSecurity.decryptId(id) < 0){
            throw new IncorrectData("Serviço não pode ser editado");
        }
        restServico.setEditavel(true);

        Set<Integer> categorias = restServico.getCategoriaServicos().stream().map(RestCategoriaServico::getCategoria).collect(Collectors.toSet());
        if(categorias.size() != restServico.getCategoriaServicos().size()){
            throw new IncorrectData("Não é permitido categorias iguais");
        }
        servicoRepository.atualizarCategoria(restServico.restParaModel());

        for(CategoriaServico cs : opServico.get().getCategorias()){
            if(Objects.nonNull(cs.getId())){
                boolean encontrou = false;
                for(RestCategoriaServico rcs : restServico.getCategoriaServicos()){
                    if(Objects.nonNull(rcs.getId())){
                        if(Objects.equals(UtilSecurity.decryptId(rcs.getId()), cs.getId())){
                            encontrou = true;
                        }
                    }
                }
                if(!encontrou){
                    servicoRepository.apagarVinculo(cs);
                }
            }
        }

        Servico servico = restServico.restParaModel();
        servico.setStatus(opServico.get().getStatus());
        servicoRepository.save(servico);
    }

    public void apagar(String id){
        Optional<Servico> servico = servicoRepository.findById(UtilSecurity.decryptId(id));
        if(servico.isEmpty()){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }

        servico.get().setStatus(new StatusServico((StatusServico.INATIVO)));
        servicoRepository.save(servico.get());
    }

    public Collection<RestServico> clienteListaSimples(String id){
        Collection<Servico> servicos = servicoRepository.clienteListaSimples(UtilSecurity.decryptId(id));
        return servicos.stream().map(Servico::modelParaRest).collect(Collectors.toList());
    }
}
