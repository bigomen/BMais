package com.bmais.gestao.restapi.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FileUtil
{
    private Map<String, String> fileExtensions = new HashMap<>();
    private static final String UTF8_BOM = "\uFEFF";

    public File toFile(String destino, byte[] conteudo) throws IOException
    {
        Path newFilePath = Paths.get(destino);
        File file = Files.createFile(newFilePath).toFile();
        FileUtils.writeByteArrayToFile(file, conteudo);
        return file;
    }

    public Resource toFile(String conteudo)
    {
        Base64 base64 = new Base64();
        byte[] byteConteudoArquivo = base64.decode(conteudo.substring(conteudo.indexOf(",")));
        return new InputStreamResource(new ByteArrayInputStream(byteConteudoArquivo));
    }

    public String toBase64(Resource resource) throws IOException
    {
        byte[] bytes = IOUtils.toByteArray(resource.getInputStream());
        Base64 base64 = new Base64();
        return base64.encodeToString(bytes);
    }

    public byte[] toByteArray(Resource resource) throws IOException
    {
        return IOUtils.toByteArray(resource.getInputStream());
    }
    
    public String toBase64(byte[] arquivo)
    {
    	 Base64 base64 = new Base64();
    	 return base64.encodeToString(arquivo);
    }
    
    public String fileName(String arquivo)
    {
    	return arquivo.substring(arquivo.indexOf("/") + 1);
    }

    public String fileExtension(String arquivo)
    {
        return fileExtensions.entrySet()
                .stream()
                .filter(x -> StringUtils.contains(arquivo, x.getKey()))
                .map(Map.Entry::getValue)
                .findFirst().orElseThrow(() -> new RuntimeException("Extensao não localizada"));
    }

    public String fileExtensionByName(String arquivo)
    {
        String extensao = arquivo.substring(arquivo.lastIndexOf("."));
        return fileExtensions.entrySet()
                .stream()
                .filter(x -> extensao.equals(x.getValue()))
                .map(Map.Entry::getValue)
                .findFirst().orElseThrow(() -> new RuntimeException("Extensao não localizada"));
    }

    public String fileMimeType(String arquivo)
    {
        String extensao = arquivo.substring(arquivo.lastIndexOf("."));

        return fileExtensions.entrySet()
                .stream()
                .filter(x -> extensao.equals(x.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().orElseThrow(() -> new RuntimeException("Extensao não localizada"));
    }

    @PostConstruct
    private void initFileExtensions()
    {
        fileExtensions.put("data:image/jpeg;base64,", ".jpeg");
        fileExtensions.put("data:image/jpg;base64", ".jpg");
        fileExtensions.put("data:image/png;base64,", ".png");
        fileExtensions.put("data:application/msword;base64", ".doc");
        fileExtensions.put("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,", ".docx");
        fileExtensions.put("data:application/pdf;base64,", ".pdf");
        fileExtensions.put("data:application/vnd.ms-excel;base64", ".xls");
        fileExtensions.put("data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64", ".xlsx");
    }

    private String removeUTF8BOM(String s)
    {
        if (s.startsWith(UTF8_BOM))
        {
            s = s.substring(1);
        }

        return s;
    }
}
