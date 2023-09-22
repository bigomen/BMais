package com.bmais.gestao.restapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.repository.BancoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.DadosBancarios;
import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.repository.DadosBancariosRepository;
import com.bmais.gestao.restapi.repository.specification.DadosBancariosSpecification;
import com.bmais.gestao.restapi.restmodel.RestDadosBancarios;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DadosBancariosService extends com.bmais.gestao.restapi.service.Service<DadosBancarios, RestDadosBancarios>
{
    private final DadosBancariosRepository dadosBancariosRepository;
    private final BancoRepository bancoRepository;

    @Autowired
    public DadosBancariosService(DadosBancariosRepository dadosBancariosRepository, BancoRepository bancoRepository) {
        super();
        this.dadosBancariosRepository = dadosBancariosRepository;
        this.bancoRepository = bancoRepository;
    }

    public Collection<RestDadosBancarios> listar(Long banco, String agencia, String conta)
    {
        Collection<DadosBancarios> dadosBancarios = dadosBancariosRepository.listar(new DadosBancariosSpecification(banco, agencia, conta));
        return toResponse(dadosBancarios);
    }

    public RestDadosBancarios detalhar(String id)
    {
        Optional<DadosBancarios> opDadosBancarios = dadosBancariosRepository.pesquisarPorId(UtilSecurity.decryptId(id));

        DadosBancarios dadosBancarios= opDadosBancarios.orElseThrow(() -> new ObjectNotFoundException("Dados bancários não localizados"));

        return dadosBancarios.modelParaRest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void ativarDesativar(String id, boolean status)
    {
        Optional<DadosBancarios> dadosBancarios = dadosBancariosRepository.findById(UtilSecurity.decryptId(id));

        dadosBancarios.ifPresent(db ->{
            db.setAtivo(status);
            dadosBancariosRepository.save(db);
        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestDadosBancarios restDadosBancarios)
    {
        DadosBancarios dadosBancarios = restDadosBancarios.restParaModel();
        dadosBancariosRepository.save(dadosBancarios);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(String id, RestDadosBancarios restDadosBancarios)
    {
        Optional<DadosBancarios> opDadosBancarios = dadosBancariosRepository.pesquisarPorId(UtilSecurity.decryptId(id));
        DadosBancarios dadosBancarios= opDadosBancarios.orElseThrow(() -> new ObjectNotFoundException("Dados bancários não localizados"));
        DadosBancarios novosDadosBancarios = restDadosBancarios.restParaModel();
        novosDadosBancarios.setAtivo(dadosBancarios.isAtivo());
        novosDadosBancarios.setId(dadosBancarios.getId());
        dadosBancariosRepository.save(novosDadosBancarios);
    }

    public Collection<RestDadosBancarios> pesquisarPorEmpresa(String id)
    {
        Collection<DadosBancarios> dadosBancarios = dadosBancariosRepository.findByEmpresa(new Empresa(UtilSecurity.decryptId(id)));
        return dadosBancarios.stream()
                .map(DadosBancarios::modelParaRest)
                .collect(Collectors.toList());
    }

    public void validarDadosBancarios(Collection<DadosBancarios> dados){
        Collection<DadosBancarios> dadosBancarios = new ArrayList<>(dados);
        Integer count = null;
        for(DadosBancarios d : dados){
            if(d.getBanco() == null || d.getBanco().getId() == null){
                throw new IncorrectData("Banco não informado!");
            }
            if(!bancoRepository.existsById(d.getBanco().getId())) throw new ObjectNotFoundException("Banco não encontrado");
            if(!StringUtils.isNotBlank(d.getAgencia()) || !StringUtils.isNotBlank(d.getConta())){
                throw new IncorrectData("Dados bancarios incompletos");
            }
            if(Objects.isNull(d.getId())){
                if(dadosBancariosRepository.validarContaCriacao(d.getConta(), d.getAgencia(), d.getBanco())){
                    throw new IncorrectData("Dados bancarios já cadastrados!");
                }
            }else {
                if(dadosBancariosRepository.validarContaAtualizacao(d.getConta(), d.getAgencia(), d.getBanco(), d.getId())){
                    throw new IncorrectData("Dados bancarios já cadastrados!");
                }
            }
            count = 0;
            for(DadosBancarios db : dadosBancarios){
                if(Objects.equals(d.getAgencia(), db.getAgencia()) && Objects.equals(d.getConta(), db.getConta()) && Objects.equals(d.getBanco(), db.getBanco())){
                    count = count + 1;
                }
            }
            if(count > 1){
                throw new IncorrectData("Dados bancarios repetidos!");
            }
        }
        dados.forEach(d -> {

        });
    }

    @Override
    protected CrudRepository<DadosBancarios, Long> getRepository()
    {
        return dadosBancariosRepository;
    }
}
