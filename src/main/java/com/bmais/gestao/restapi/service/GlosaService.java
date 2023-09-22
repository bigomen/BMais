package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Glosa;
import com.bmais.gestao.restapi.model.StatusGlosa;
import com.bmais.gestao.restapi.model.enums.Responsabilidade;
import com.bmais.gestao.restapi.repository.GlosaRepository;
import com.bmais.gestao.restapi.restmodel.RestGlosa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GlosaService extends com.bmais.gestao.restapi.service.Service<Glosa, RestGlosa> {

    private final GlosaRepository glosaRepository;

    @Autowired
    public GlosaService(GlosaRepository glosaRepository) {
        super();
        this.glosaRepository = glosaRepository;
    }

    public Collection<RestGlosa> listar(String codigo, Responsabilidade responsabilidade) {
        Collection<Glosa> lista = glosaRepository.pesquisar(codigo, responsabilidade);
        return lista.stream().map(Glosa::modelParaRest).collect(Collectors.toList());
    }

    public RestGlosa detalhes(String id) {
        return super.getById(id);
    }

    public RestGlosa inserir(RestGlosa restGlosa) {
        if(Objects.nonNull(glosaRepository.findByCodigo(restGlosa.getCodigo()))){
            throw  new ObjectAlreadyInBase("Codigo já utilizado por outra Glosa");
        }
        return super.createOrUpdate(restGlosa);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(RestGlosa restGlosa, String id) {
        boolean existsById = glosaRepository.existsById(UtilSecurity.decryptId(id));
        if (!existsById) {
            throw new ObjectNotFoundException(MensagensID.PTR080);
        }
        restGlosa.setId(id);
        Glosa glosa = restGlosa.restParaModel();
        if (glosaRepository.validarCodigo(glosa.getCodigo(), glosa.getId())){
            throw new ObjectAlreadyInBase("Código já utilizado por outra Glosa");
        }
        glosaRepository.save(glosa);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(String id) {
        Glosa glosa = glosaRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR080));
        glosa.setStatus(new StatusGlosa(StatusGlosa.INATIVO));
        glosaRepository.save(glosa);
    }

    @Override
    protected CrudRepository<Glosa, Long> getRepository() {
        return glosaRepository;
    }
}
