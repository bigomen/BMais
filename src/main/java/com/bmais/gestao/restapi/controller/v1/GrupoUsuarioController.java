package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.restmodel.RestGrupoUsuario;
import com.bmais.gestao.restapi.service.GrupoUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/grupoUsuario/v1")
@Validated
public class GrupoUsuarioController {

    private final GrupoUsuarioService grupoUsuarioService;

    public GrupoUsuarioController(GrupoUsuarioService grupoUsuarioService) {
        this.grupoUsuarioService = grupoUsuarioService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lista")
    public Collection<RestGrupoUsuario> listar(){
        return grupoUsuarioService.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lista_simples")
    public Collection<RestGrupoUsuario> listarSimples(){
        return grupoUsuarioService.listaSimples();
    }
}

//package com.bmais.gestao.restapi.controller.v1;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.Valid;
//import javax.validation.ValidationException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bmais.gestao.restapi.base.BaseController;
//import com.bmais.gestao.restapi.model.GrupoUsuario;
//import com.bmais.gestao.restapi.model.Perfil;
//import com.bmais.gestao.restapi.repository.GrupoUsuarioRepository;
//import com.bmais.gestao.restapi.restmodel.RestGrupoUsuario;
//import com.bmais.gestao.restapi.restmodel.RestPerfil;
//import com.bmais.gestao.restapi.utility.ErrorMessage;
//import com.bmais.gestao.restapi.utility.RestSecurity;
//
//@RestController
//public class GrupoUsuarioController extends BaseController<GrupoUsuario, RestGrupoUsuario> {
//
//    @Autowired
//    private Environment env;
//
//    @Autowired
//    private GrupoUsuarioRepository grupoUsuarioRepository;
//
//
//    @Override
//    protected CrudRepository getRepository() {
//        return grupoUsuarioRepository;
//    }
//
//    @Override
//    protected RestSecurity<GrupoUsuario, RestGrupoUsuario> getRestSecurity() {
//        return new RestSecurity<>(GrupoUsuario.class, RestGrupoUsuario.class, env);
//    }
//
//    protected RestSecurity<Perfil, RestPerfil> getRestSecurityPerfil() {
//        return new RestSecurity<>(Perfil.class, RestPerfil.class, env);
//    }
//
//    @Transactional
//    @PostMapping(value = "empresa/v1/grupo-usuario/novo")
//    public ResponseEntity<RestGrupoUsuario> novo(@Valid @RequestBody RestGrupoUsuario value) {
//        return _novo(value, super.getIdEmpresa());
//    }
//    
//    
//    public ResponseEntity<RestGrupoUsuario> _novo(RestGrupoUsuario value, Long idEmpresa) {
//        try {
//            if (value.getId() == null) {
//                GrupoUsuario grp = (GrupoUsuario) getRestSecurity().copyToDbObject(value);
//                grp.setId(null);
//                grp.setEmpId(idEmpresa);
//
//                if (value.getPerfis() != null) {
//                    for (RestPerfil rPerfil : value.getPerfis()) {
//                        Perfil p = getRestSecurityPerfil().copyToDbObject(rPerfil);
//                        grp.addPerfis(p);
//                    }
//                }
//                getRepository().save(grp);
//                RestGrupoUsuario restGrp = getRestSecurity().copyToRestObject(grp);
//                return new ResponseEntity<>(restGrp, HttpStatus.OK);
//
//            }
//        } catch (Exception ex) {
//            throw new ValidationException(ex.getMessage());
//        }
//        throw new ValidationException("Object can not be created");
//
//    }
//
//    @Transactional
//    @GetMapping(value = "empresa/v1/grupo-usuario/lista")
//    public ResponseEntity<Iterable<RestGrupoUsuario>> lista() {
//        try {
//            List<GrupoUsuario> lstGrp = ((GrupoUsuarioRepository) getRepository()).findByEmpIdOrderByGusNome(super.getIdEmpresa());
//            Iterable<RestGrupoUsuario> restLstGrp = getRestSecurity().copyToRestObject(lstGrp);
//            return new ResponseEntity<>(restLstGrp, HttpStatus.OK);
//        } catch (Exception ex) {
//            throw new ValidationException(ex.getMessage());
//        }
//    }
//
//    @Transactional
//    @PutMapping(value = "empresa/v1/grupo-usuario/atualiza")
//    public ResponseEntity<String> atualiza(@Valid @RequestBody RestGrupoUsuario value) {
//        try {
//            Long grpDecryp = getRestSecurity().decryptId(value.getId());
//            if (value.getId() != null) {
//                Optional<GrupoUsuario> optGrp = grupoUsuarioRepository.findByIdAndEmpId(grpDecryp, super.getIdEmpresa());
//                if (optGrp.isPresent()) {
//                    GrupoUsuario grp = optGrp.get();
//                    grp.setGusNome(value.getGusNome());
//                    grp.setEmpId(super.getIdEmpresa());
//
//                    if (value.getPerfis() != null) {
//                        for (Perfil p : new HashSet<Perfil>(grp.getPerfis())) {
//                            grp.removePerfil(p);
//                        }
//
//                        for (RestPerfil rPerfil : value.getPerfis()) {
//                            Perfil p = getRestSecurityPerfil().copyToDbObject(rPerfil);
//                            grp.addPerfis(p);
//                        }
//                    }
//
//                    grupoUsuarioRepository.save(grp);
//                    return new ResponseEntity<>(null, HttpStatus.OK);
//                }
//            }
//
//        } catch (Exception ex) {
//            throw new ValidationException(ex.getMessage());
//        }
//        throw new ValidationException("Object can not be created");
//    }
//
//    @Transactional
//    @DeleteMapping(value = "empresa/v1/grupo-usuario/remove/{id}")
//    public ResponseEntity<ErrorMessage> remove(@PathVariable String id) {
//        try {
//            Optional<GrupoUsuario> optGrp = grupoUsuarioRepository.findByIdAndEmpId(getRestSecurity().decryptId(id), super.getIdEmpresa());
//            if (optGrp.isPresent()) {
//                GrupoUsuario grp = optGrp.get();
//                if (!grp.getUsuarios().isEmpty())
//                    return new ResponseEntity<>(new ErrorMessage("Grupo possui usuários. Altere o grupo dos usuários antes de remover.", "400"), HttpStatus.OK);
//
//                for (Perfil p : new HashSet<Perfil>(grp.getPerfis())) {
//                    grp.removePerfil(p);
//                }
//                
//                grupoUsuarioRepository.delete(grp);
//                return new ResponseEntity<>(new ErrorMessage("OK", "200"), HttpStatus.OK);
//            }        
//        } catch (Exception ex) {
//            throw new ValidationException(ex.getMessage());
//        }
//        throw new ValidationException("Object not found");
//    }
//
//    @Transactional
//    @GetMapping(value = "empresa/v1/grupo-usuario/recupera/{id}")
//    public ResponseEntity<RestGrupoUsuario> recupera(@PathVariable String id) {
//        try {
//            Optional<GrupoUsuario> optGrp = grupoUsuarioRepository.findByIdAndEmpId(getRestSecurity().decryptId(id), super.getIdEmpresa());
//            if (optGrp.isPresent()) {
//                GrupoUsuario grp = optGrp.get();
//                RestGrupoUsuario rGrp = getRestSecurity().copyToRestObject(grp);
//                
//                List<RestPerfil> rPerfis = getRestSecurityPerfil().copyToRestObject(grp.getPerfis());
//                rGrp.setPerfis(rPerfis);
//                
//                
//                return new ResponseEntity<>(rGrp, HttpStatus.OK);
//            }
//        } catch (Exception ex) {
//            throw new ValidationException(ex.getMessage());
//        }
//        throw new ValidationException("Object not found");
//    }
//
//}
