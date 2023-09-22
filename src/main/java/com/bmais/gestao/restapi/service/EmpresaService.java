package com.bmais.gestao.restapi.service;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.DadosBancarios;
import com.bmais.gestao.restapi.model.Empresa;
import com.bmais.gestao.restapi.model.StatusEmpresa;
import com.bmais.gestao.restapi.repository.DadosBancariosRepository;
import com.bmais.gestao.restapi.repository.EmpresaRepository;
import com.bmais.gestao.restapi.restmodel.RestEmpresa;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EmpresaService extends com.bmais.gestao.restapi.service.Service<Empresa, RestEmpresa>
{

    private final EmpresaRepository empresaRepository;

    private final DadosBancariosRepository dadosBancariosRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository, DadosBancariosRepository dadosBancariosRepository){
        super();
        this.empresaRepository = empresaRepository;
        this.dadosBancariosRepository = dadosBancariosRepository;
    }

//    public Collection<RestEmpresa> listar(){
//        Collection<Empresa> empresas = empresaRepository.listar();
//        for(Empresa e : empresas){
//            if (e.getEmpresa() != null){
//                Empresa em = new Empresa();
//                em.setId(e.getEmpresa().getId());
//                e.setEmpresa(em);
//            }
//        }
//        return empresas.stream().map(Empresa::modelParaRest).collect(Collectors.toList());
//    }

    public Collection<RestEmpresa> listar(){
        Collection<Empresa> empresas = empresaRepository.listarEmpresas();
        return empresas.stream().map(Empresa::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestEmpresa> listarSedes(){
        Collection<Empresa> empresas = empresaRepository.listarSedes();
        return empresas.stream().map(Empresa::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestEmpresa>listarFiliais(String id){
        Collection<Empresa> empresas = empresaRepository.listarFiliais(UtilSecurity.decryptId(id));
        for(Empresa e : empresas){
            if (e.getEmpresa() != null){
                Empresa em = new Empresa();
                em.setId(e.getEmpresa().getId());
                e.setEmpresa(em);
            }
        }
        return empresas.stream().map(Empresa::modelParaRest).collect(Collectors.toList());
    }

    public RestEmpresa detalhes(String id){
        Optional<Empresa> empresas = empresaRepository.findById(UtilSecurity.decryptId(id));
        if(empresas.isEmpty()){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        if (empresas.get().getEmpresa() != null){
            Empresa em = new Empresa();
            em.setId(empresas.get().getEmpresa().getId());
            empresas.get().setEmpresa(em);
        }
        Collection<DadosBancarios> dadosBancarios = dadosBancariosRepository.findByEmpresa(empresas.get());
        empresas.get().setDadosBancarios(dadosBancarios);
        return empresas.get().modelParaRest();
    }

    public Collection<RestEmpresa> pesquisarEmpresasDadosBancarios()
    {
        Collection<Empresa> empresas = empresaRepository.pesquisarEmpresasComDadosBancarios();
        return toResponse(empresas);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestEmpresa restEmpresa){
        if(empresaRepository.validarCadastro(restEmpresa.getRazaoSocial(), restEmpresa.getCnpj())){
            throw new ObjectAlreadyInBase("Razão Social/CNPJ já cadastrado");
        }
        empresaRepository.save(restEmpresa.restParaModel());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestEmpresa restEmpresa, String id){
        if(!empresaRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        restEmpresa.setId(id);
        Empresa empresa = restEmpresa.restParaModel();
        if(empresaRepository.validarRazaoCnpj(empresa.getRazaoSocial(), empresa.getCnpj(), empresa.getId())){
            throw new ObjectAlreadyInBase("Razao/CNPJ já utilizados por outra empresa");
        }
        empresaRepository.save(empresa);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(String id){
        Empresa empresa = empresaRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR032));
        empresa.setStatus(new StatusEmpresa(StatusEmpresa.INATIVO));
        empresaRepository.save(empresa);
    }

    public Collection<RestEmpresa> listaSimples(){
        Collection<Empresa> empresas = empresaRepository.listaSimples();
        return empresas.stream().map(Empresa::modelParaRest).collect(Collectors.toList());
    }

    @Override
    protected CrudRepository<Empresa, Long> getRepository()
    {
        return empresaRepository;
    }
}
