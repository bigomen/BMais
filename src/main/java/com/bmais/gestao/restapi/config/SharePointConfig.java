package com.bmais.gestao.restapi.config;

import com.panxoloto.sharepoint.rest.PLGSharepointClient;
import com.panxoloto.sharepoint.rest.PLGSharepointClientOnline;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharePointConfig
{
    @Value(value = "${sharepoint.email}")
    private String email;

    @Value(value = "${sharepoint.senha}")
    private String senha;

    @Value(value = "${sharepoint.dominio}")
    private String dominio;

    @Value(value = "${sharepoint.site}")
    private String site;

    @Bean
    public PLGSharepointClient sharepointClient()
    {
        try
        {
            return new PLGSharepointClientOnline(email, senha, dominio, site);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}