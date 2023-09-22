package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.NF;
import com.bmais.gestao.restapi.repository.NFRepository;
import com.bmais.gestao.restapi.restmodel.RestNF;
import com.bmais.gestao.restapi.restmodel.RestNFPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class NFService {

    private final NFRepository nfRepository;

    @Autowired
    public NFService(NFRepository nfRepository) {
        this.nfRepository = nfRepository;
    }

    public Collection<RestNF> listar(RestNFPesquisa params){
        Collection<NF> nfs = nfRepository.listar(params);
        return nfs.stream().map(nf -> nf.modelParaRest()).collect(Collectors.toList());
    }

    public RestNF detalhes(String id){
        if(!nfRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.PTR032);
        }else {
            NF nf = nfRepository.detalhesNF(UtilSecurity.decryptId(id));
            return nf.modelParaRest();
        }

    }

    public RestNF novo(RestNF restNF){
        NF nf = nfRepository.save(restNF.restParaModel());
        return nf.modelParaRest();
    }

    public RestNF atualizar(RestNF restNF, String id){
        if(!nfRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.PTR032);
        }else{
            restNF.setId(id);
            nfRepository.save(restNF.restParaModel());
            return restNF;
        }
    }
}
