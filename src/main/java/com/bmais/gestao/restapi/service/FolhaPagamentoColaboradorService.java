package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.FolhaPagamento;
import com.bmais.gestao.restapi.model.FolhaPagamentoColaborador;
import com.bmais.gestao.restapi.repository.FolhaPagamentoColaboradorRepository;
import com.bmais.gestao.restapi.repository.FolhaPagamentoRepository;
import com.bmais.gestao.restapi.repository.PagamentosGeradosRepository;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamento;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaborador;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorPesquisa;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorView;
import com.bmais.gestao.restapi.utility.UtilData;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class FolhaPagamentoColaboradorService {

    private final FolhaPagamentoRepository folhaPagamentoRepository;
    private final FolhaPagamentoColaboradorRepository folhaPagamentoColaboradorRepository;
    private final PagamentosGeradosRepository pagamentosGeradosRepository;

    public FolhaPagamentoColaboradorService(FolhaPagamentoRepository folhaPagamentoRepository, FolhaPagamentoColaboradorRepository folhaPagamentoColaboradorRepository, PagamentosGeradosRepository pagamentosGeradosRepository) {
        this.folhaPagamentoRepository = folhaPagamentoRepository;
        this.folhaPagamentoColaboradorRepository = folhaPagamentoColaboradorRepository;
        this.pagamentosGeradosRepository = pagamentosGeradosRepository;
    }

    public Collection<RestFolhaPagamentoColaborador> lista(RestFolhaPagamentoColaboradorPesquisa params){
        Collection<FolhaPagamentoColaborador> folhas = folhaPagamentoColaboradorRepository.lista(params);
        return folhas.stream().map(FolhaPagamentoColaborador::modelParaRest).collect(Collectors.toList());
    }

    public RestFolhaPagamentoColaboradorView detalhes(String id){
        RestFolhaPagamentoColaboradorView folhaView = new RestFolhaPagamentoColaboradorView();
        FolhaPagamento folha = folhaPagamentoRepository.buscarFolha(folhaPagamentoColaboradorRepository.buscarIdFolha(UtilSecurity.decryptId(id)));
        folhaView.setFolhaPagamento(folha.modelParaRest());
        Collection<FolhaPagamentoColaborador> folhasColaborador = folhaPagamentoColaboradorRepository.buscarFolhas(folha.getId());
        folhasColaborador.forEach(f -> {
            f.setPagamentosGerados(pagamentosGeradosRepository.buscarPagamentos(f.getId()));
        });
        folhaView.setFolhaColaborador(folhasColaborador.stream().map(FolhaPagamentoColaborador::modelParaRest).collect(Collectors.toList()));
        return folhaView;
    }

    public String gerarDocumento(Long numero){
        String number = String.format("%05d", numero);
        return "FPC" + number;
    }

    public FolhaPagamento novaFolha(RestFolhaPagamento restFolhaPagamento){
        restFolhaPagamento.setDataEmissao(UtilData.obterDataHoraAtual().toLocalDate());
        return folhaPagamentoRepository.save(restFolhaPagamento.restParaModel());
    }

    public void novo(RestFolhaPagamentoColaboradorView folha){
        FolhaPagamento folhaPagamento = novaFolha(folha.getFolhaPagamento());
        Collection<FolhaPagamentoColaborador> folhasColaborador = folha.getFolhaColaborador().stream().map(RestFolhaPagamentoColaborador::restParaModel).collect(Collectors.toList());
        folhasColaborador.forEach(f -> f.setFolhaPagamento(folhaPagamento));
        folhasColaborador.forEach(f -> {
            f.getPagamentosGerados().setFolhaPagamentoColaborador(f);
            f.getPagamentosGerados().setDataEmissao(UtilData.obterDataHoraAtual().toLocalDate());
            f.getPagamentosGerados().setMovimentacaoBancaria(folhaPagamentoRepository.movimentacaoBancaria());
            f.getPagamentosGerados().setLancamento(f.getPagamentosGerados().getMovimentacaoBancaria());
            f.getPagamentosGerados().setDocumento(gerarDocumento(folhaPagamentoRepository.registrarDocumento()));
        });
        try {
            folhaPagamentoColaboradorRepository.saveAll(folhasColaborador);
        }catch (Exception e){
            folhaPagamentoRepository.delete(folhaPagamento);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestFolhaPagamentoColaboradorView folha){
        if(!folhaPagamentoRepository.existsById(UtilSecurity.decryptId(folha.getFolhaPagamento().getId()))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        FolhaPagamento folhaPagamento = folhaPagamentoRepository.buscarFolha(UtilSecurity.decryptId(folha.getFolhaPagamento().getId()));
        folhaPagamentoRepository.save(folha.getFolhaPagamento().restParaModel());
        try {
            folhaPagamentoColaboradorRepository.saveAll(folha.getFolhaColaborador().stream().map(RestFolhaPagamentoColaborador::restParaModel).collect(Collectors.toList()));
        }catch (Exception e){
            folhaPagamentoRepository.save(folhaPagamento);
        }
    }
}
