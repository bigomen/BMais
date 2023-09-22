package com.bmais.gestao.restapi.repository.custom;


import com.bmais.gestao.restapi.model.*;
import com.bmais.gestao.restapi.restmodel.RestFolhaPagamentoColaboradorPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FolhaPagamentoColaboradorRepositoryCustomImpl extends Repository<FolhaPagamentoColaborador> implements FolhaPagamentoColaboradorRepositoryCustom {

    private final PagamentoGeradoRepository pagamentoGeradoRepository;

    public FolhaPagamentoColaboradorRepositoryCustomImpl(PagamentoGeradoRepository pagamentoGeradoRepository) {
        this.pagamentoGeradoRepository = pagamentoGeradoRepository;
    }

    @Override
    public Collection<FolhaPagamentoColaborador> lista(RestFolhaPagamentoColaboradorPesquisa params){
        CriteriaQuery<FolhaPagamentoColaborador> criteria = super.getCriteria();
        Root<FolhaPagamentoColaborador> root = criteria.from(getClazz());
        final Predicate[] conjunction = {builder().conjunction()};

        Join<FolhaPagamentoColaborador, Colaborador> joinColaborador = root.join(FolhaPagamentoColaborador_.COLABORADOR);
        Join<FolhaPagamentoColaborador, TipoPagamentoColaborador> joinTipoPagamento = root.join(FolhaPagamentoColaborador_.TIPO_PAGAMENTO);

        Path<Long> idFolha = root.get(FolhaPagamentoColaborador_.ID);
        Path<Long> idColaborador = joinColaborador.get(Colaborador_.ID);
        Path<String> nomeColaborador = joinColaborador.get(Colaborador_.NOME);
        Path<Long> idTipoPagamento = joinTipoPagamento.get(TipoPagamentoColaborador_.ID);
        Path<String> tipoPagamento = joinTipoPagamento.get(TipoPagamentoColaborador_.DESCRICAO);
        Path<BigDecimal> valor = root.get(FolhaPagamentoColaborador_.VALOR);

        criteria.multiselect(idFolha, idColaborador, nomeColaborador, idTipoPagamento, tipoPagamento, valor);

        if(StringUtils.isNotBlank(params.getTipoPagamento())){
            Predicate equal = builder().equal(joinTipoPagamento.get(TipoPagamentoColaborador_.ID), UtilSecurity.decryptId(params.getTipoPagamento()));
            conjunction[0] = builder().and(conjunction[0], equal);
        }
        if(params.getPeriodoVencimentoInicial() != null && params.getPeriodoVencimentoFinal() != null){
            Join<FolhaPagamentoColaborador, PagamentosGerados> joinPagamentos = root.join(FolhaPagamentoColaborador_.PAGAMENTOS_GERADOS);
            Predicate between = builder().between(joinPagamentos.get(PagamentosGerados_.DATA_VENCIMENTO), params.getPeriodoVencimentoInicial(), params.getPeriodoVencimentoFinal());
            conjunction[0] = builder().and(conjunction[0], between);
        }
        if(params.getColaboradores() != null){
            String[] divColab = params.getColaboradores().split("_");
            Collection<String> colaboradores = new ArrayList<>(List.of(divColab));
            colaboradores.forEach(c -> {
                Predicate equal = builder().equal(joinColaborador.get(Colaborador_.ID), UtilSecurity.decryptId(c));
                conjunction[0] = builder().or(conjunction[0], equal);
            });
        }
        criteria.where(conjunction[0]);
        criteria.orderBy(builder().asc(nomeColaborador));
        TypedQuery<FolhaPagamentoColaborador> typedQuery = entityManager.createQuery(criteria);
        Collection<FolhaPagamentoColaborador> folhas = typedQuery.getResultList();
        folhas.forEach(f -> {
            f.setPagamentosGerados(pagamentoGeradoRepository.buscarPagamento(f.getId()));
        });
        return folhas;
    }

    @Override
    public Class<FolhaPagamentoColaborador> getClazz(){
        return FolhaPagamentoColaborador.class;
    }
}
