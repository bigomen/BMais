package com.bmais.gestao.restapi.mapper;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Endereco;
import com.bmais.gestao.restapi.model.Municipio;
import com.bmais.gestao.restapi.repository.MunicipioRepository;
import com.bmais.gestao.restapi.restmodel.RestEndereco;
import com.bmais.gestao.restapi.restmodel.RestMunicipio;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Component
public class EnderecoMapper
{
    private static MunicipioRepository municipioRepository;

    @Autowired
    protected EnderecoMapper(MunicipioRepository municipioRepository)
    {
        EnderecoMapper.municipioRepository = municipioRepository;
    }

    public static RestEndereco convertToRest(Endereco endereco)
    {
        if(endereco == null)
        {
            return null;
        }

        RestMunicipio municipio = MunicipioMapper.INSTANCE.convertToRest(endereco.getMunicipio());

        RestEndereco restEndereco = new RestEndereco();
        restEndereco.setId(UtilSecurity.encryptId(endereco.getId()));
        restEndereco.setLogradouro(endereco.getLogradouro());
        restEndereco.setComplemento(endereco.getComplemento());
        restEndereco.setBairro(endereco.getBairro());
        restEndereco.setCep(endereco.getCep());
        restEndereco.setNumero(endereco.getNumero());
        restEndereco.setMunicipio(municipio);
        return restEndereco;
    }

    public static Endereco convertToModel(RestEndereco restEndereco)
    {
        if(restEndereco == null)
        {
            return null;
        }

        Optional<Municipio> municipio = municipioRepository.findByNomeAndUfSigla(restEndereco.getMunicipio().getNome(), restEndereco.getMunicipio().getUf().getSigla());
        Endereco endereco = new Endereco();
        endereco.setMunicipio(municipio.orElseThrow(() -> new ObjectNotFoundException(MensagensID.MunicipioNotFound)));
        endereco.setBairro(restEndereco.getBairro());
        endereco.setLogradouro(restEndereco.getLogradouro());
        endereco.setComplemento(restEndereco.getComplemento());
        endereco.setCep(restEndereco.getCep());
        endereco.setNumero(restEndereco.getNumero());
        endereco.setId(UtilSecurity.decryptId(restEndereco.getId()));
        return endereco;
    }
}
