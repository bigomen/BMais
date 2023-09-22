package com.bmais.gestao.restapi.controller.v1;

import com.bmais.gestao.restapi.service.DocumentoService;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documentos/v1")
public class DocumentoController {

    private final DocumentoService documentoService;

    @Autowired
    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<byte[]> download(@PathVariable String id)
    {
        Object documento[] = documentoService.pesquisarDocumento(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + (String) documento[0] + "\"")
                .body((byte[])documento[2]);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/download/base64/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<String> downloadBase64(@PathVariable String id)
    {
        Object[] documento = documentoService.pesquisarDocumento(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + (String) documento[0] + "\"")
                .body((String) documento[1]);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable String id)
    {
        documentoService.excluirDocumento(id);
    }
}
