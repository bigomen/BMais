package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.Documento;
import com.bmais.gestao.restapi.model.PessoaJuridica;
import com.bmais.gestao.restapi.repository.DocumentoRepository;
import com.bmais.gestao.restapi.restmodel.RestDocumento;
import com.bmais.gestao.restapi.utility.FileUtil;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import com.panxoloto.sharepoint.rest.PLGSharepointClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DocumentoService {

    @Value(value = "${sharepoint.caminho}")
    private String CAMINHO_SHAREPOINT;

    @Value(value = "${sharepoint.site}")
    private String SITE_SHAREPOINT;

    private final PLGSharepointClient sharepointClient;
    private final DocumentoRepository documentoRepository;
    private final FileUtil fileUtil;

    @Autowired
    public DocumentoService(PLGSharepointClient sharepointClient, DocumentoRepository documentoRepository, FileUtil fileUtil)
    {
        this.sharepointClient = sharepointClient;
        this.documentoRepository = documentoRepository;
        this.fileUtil = fileUtil;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Documento salvarDocumento(RestDocumento restDocumento)
    {
        Documento documento = restDocumento.restParaModel();
        documento.setDescricao(documento.getTipo() + "_" + restDocumento.getIdentificador() + "_");
        return documentoRepository.save(documento);
    }

    public String fileExtension(String conteudo)
    {
        return fileUtil.fileExtension(conteudo);
    }

    public String fileExtensionByName(String arquivo)
    {
        return fileUtil.fileExtensionByName(arquivo);
    }

    public String fileMimeType(String arquivo)
    {
        return fileUtil.fileMimeType(arquivo);
    }

    public void upload(String pasta, String nome, String conteudo)
    {
        Resource resource = fileUtil.toFile(conteudo);

        try
        {
            sharepointClient.uploadFile(CAMINHO_SHAREPOINT + pasta, resource, nome, new JSONObject());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void upload(String pasta, Collection<Documento> documentos)
    {
        documentos.forEach(doc -> {
            upload(pasta, doc.getDescricao().substring(doc.getDescricao().lastIndexOf("/") + 1), doc.getImagem());
        });
    }

    public Optional<String> download(String arquivo)
    {
        try
        {
            InputStreamResource resource = sharepointClient.downloadFile(SITE_SHAREPOINT + "/" + CAMINHO_SHAREPOINT + arquivo);
            return Optional.of(fileUtil.toBase64(resource));
        } catch (Exception e)
        {
            return Optional.empty();
        }
    }

    public byte[] downloadDocumento(String arquivo)
    {
        try
        {
            InputStreamResource resource = sharepointClient.downloadFile(SITE_SHAREPOINT + "/" + CAMINHO_SHAREPOINT + arquivo);
            return fileUtil.toByteArray(resource);

        }catch (Exception e)
        {
            throw new ObjectNotFoundException(MensagensID.PTR024);
        }
    }

    public Object [] pesquisarDocumento(String id)
    {
        Documento documento = documentoRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR024));
        
        String fileMimeType = fileMimeType(documento.getDescricao());
        
        byte[] arquivo = downloadDocumento(documento.getDescricao());
        String encodeToString = fileUtil.toBase64(arquivo);
        
        Object[] array = new Object[3];
        array[0] = fileUtil.fileName(documento.getDescricao());
        array[1] = fileMimeType + encodeToString;
        array[2] = arquivo;
        return array;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void excluirDocumento(String id)
    {
        Documento documento = documentoRepository.findById(UtilSecurity.decryptId(id))
                .orElseThrow(() -> new ObjectNotFoundException(MensagensID.PTR024));

        documentoRepository.delete(documento);
        excluir(documento.getDescricao());
    }

    public void excluir(String arquivo)
    {
        try
        {
            sharepointClient.deleteFile(SITE_SHAREPOINT + CAMINHO_SHAREPOINT + "/" + arquivo);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Collection<Documento> documentosPessoaJuridica(PessoaJuridica pessoaJuridica)
    {
        return documentoRepository.documentosPessoaJuridica(pessoaJuridica);
    }
}
