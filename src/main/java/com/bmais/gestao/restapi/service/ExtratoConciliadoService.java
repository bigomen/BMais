package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.model.NF;
import com.bmais.gestao.restapi.model.Pagamento;
import com.bmais.gestao.restapi.repository.NFRepository;
import com.bmais.gestao.restapi.restmodel.RestPagamento;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ExtratoConciliadoService
{
    private final NFRepository nfRepository;

    @Autowired
    public ExtratoConciliadoService(NFRepository nfRepository)
    {
        this.nfRepository = nfRepository;
    }

    public Collection<RestPagamento> pesquisarExtratosNaoConsolidados(String empresa, String dadosBancarios)
    {
        Collection<NF> nfs = pesquisarNFs(UtilSecurity.decryptId(empresa), UtilSecurity.decryptId(dadosBancarios));
        return nfs.stream()
                .map(Pagamento::convertToPagamento)
                .collect(Collectors.toList());
    }

    private Collection<NF> pesquisarNFs(Long empresa, Long dadosBancarios)
    {
        Collection<NF> nfs = nfRepository.pesquisarNotasFiscais(empresa, dadosBancarios);
        return nfs;
    }
}
