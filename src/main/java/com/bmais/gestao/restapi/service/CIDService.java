package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.CID;
import com.bmais.gestao.restapi.model.StatusCID;
import com.bmais.gestao.restapi.repository.CIDRepository;
import com.bmais.gestao.restapi.restmodel.RestCID;
import com.bmais.gestao.restapi.restmodel.RestCIDPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CIDService extends com.bmais.gestao.restapi.service.Service<CID, RestCID> {

    private final CIDRepository cidRepository;

    @Autowired
    public CIDService(CIDRepository cidRepository) {
        super();
        this.cidRepository = cidRepository;
    }

    public Page<RestCID> listar(int pagina, RestCIDPesquisa params) {
        Page<CID> cids = cidRepository.lista(pagina, params);
        return cids.map(CID::modelParaRest);
    }

    public RestCID detalhes(String id){
        Optional<CID> cid = cidRepository.findById(Long.valueOf(id));
        if(cid.isEmpty()) throw new ObjectNotFoundException("CID não encontrado"); else return cid.get().modelParaRest();
    }

    public Collection<RestCID> listaSimples(){
        Collection<CID> cids = cidRepository.listaSimples();
        return cids.stream().map(CID::modelParaRest).collect(Collectors.toList());
    }

    public RestCID novo(RestCID restCID) {
        return super.createOrUpdate(restCID);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(String id, RestCID updateCID) {
        cidRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR040));
        updateCID.setId(id);
        cidRepository.save(updateCID.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        CID cid = cidRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR040));
        cid.setStatus(new StatusCID(StatusCID.INATIVO));
        cidRepository.save(cid);
    }

    @Override
    protected CrudRepository<CID, Long> getRepository() {
        return cidRepository;
    }
}
