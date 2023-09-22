package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.CadastroJaExistente;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.StatusTuss;
import com.bmais.gestao.restapi.model.Tuss;
import com.bmais.gestao.restapi.repository.TussRepository;
import com.bmais.gestao.restapi.restmodel.RestTuss;
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
public class TussService extends com.bmais.gestao.restapi.service.Service<Tuss, RestTuss> {

    private final TussRepository tussRepository;

    @Autowired
    public TussService(TussRepository tussRepository) {
        super();
        this.tussRepository = tussRepository;
    }

    public RestTuss detalhes(String id) {
        Optional<Tuss> tuss = tussRepository.findById(Long.valueOf(id));
        if(tuss.isEmpty()){
            throw new ObjectNotFoundException("Tuss não encontrado");
        }
        return tuss.get().modelParaRest();
    }

    public Page<RestTuss> pesquisar(String codigo, String descricao, Long status, String orderBy, int pagina, int pageSize) {
        Page<Tuss> tuss = tussRepository.pesquisar(codigo, descricao, status, orderBy, pagina, pageSize);
        return tuss.map(Tuss::modelParaRest);
    }

    public void novo(RestTuss restTuss) {
        if(tussRepository.existsByCodigo(restTuss.getCodigo())) throw new CadastroJaExistente("Codigo já utilizado");
        tussRepository.save(restTuss.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(String id, RestTuss restTuss) {
        if(!tussRepository.existsById(Long.parseLong(id))) throw new ObjectNotFoundException("Tuss não encontrado");
        if(tussRepository.validarCodigoUpdate(restTuss.getCodigo(), Long.valueOf(id)))
            throw new CadastroJaExistente("Codigo já utilizado em outro registro");
        restTuss.setId(id);
        tussRepository.save(restTuss.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(String id) {
        Tuss tuss = tussRepository.findById(UtilSecurity.decryptId(id)).
                orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR060));
        tuss.setStatus(new StatusTuss(StatusTuss.INATIVO));
        tussRepository.save(tuss);
    }

    public Collection<RestTuss> listaSimples(){
        Collection<Tuss> tussList = tussRepository.listaSimples();
        return tussList.stream().map(Tuss::modelParaRest).collect(Collectors.toList());
    }

    @Override
    protected CrudRepository<Tuss, Long> getRepository() {
        return tussRepository;
    }
}
