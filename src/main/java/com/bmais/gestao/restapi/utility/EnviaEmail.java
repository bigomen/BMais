package com.bmais.gestao.restapi.utility;

import com.sendgrid.Method;
import com.sendgrid.SendGrid;
import com.sendgrid.Request;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class EnviaEmail {

    @Autowired
    private Environment env;

    
    public Mensagem novaMensagem() {
        return new Mensagem();
    }
    
    public void enviar(Mensagem mensagem) {

        boolean habilitaEnvio = env.getProperty("email.habilitaEnvio", Boolean.class);
        
        if (!habilitaEnvio)
            return;
        
        String sendgridKey = env.getProperty("sendgrid.key");
        
        Email emailFrom = new Email(mensagem.getEmailOrigem());
        if (mensagem.getNomeOrigem()!= null)
            emailFrom.setName(mensagem.getNomeOrigem());
        
        Email emailTo = new Email(mensagem.getEmailDestinatario());
        if (mensagem.getNomeDestinatario()!= null)
            emailTo.setName(mensagem.getNomeDestinatario());
        
        Content content = new Content("text/html", mensagem.getMensagem());
        Mail mail = new Mail(emailFrom, mensagem.getAssunto(), emailTo, content);

        try {
          Request request = new Request();
          request.setMethod(Method.POST);
          request.setEndpoint("mail/send");
          request.setBody(mail.build());
          
          SendGrid sg = new SendGrid(sendgridKey);
          sg.api(request);
        } catch (IOException me) {
            me.printStackTrace();
        }

    }

    
    public class Mensagem {
        private String assunto;
        private String mensagem;
        private String emailOrigem;
        private String nomeOrigem;
        private String nomeDestinatario;
        private String emailDestinatario;

        public String getAssunto() {
            return assunto;
        }

        public void setAssunto(String assunto) {
            this.assunto = assunto;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getEmailOrigem() {
            return emailOrigem;
        }

        public void setEmailOrigem(String emailOrigem) {
            this.emailOrigem = emailOrigem;
        }

        public String getNomeOrigem() {
            return nomeOrigem;
        }

        public void setNomeOrigem(String nomeOrigem) {
            this.nomeOrigem = nomeOrigem;
        }

        public String getNomeDestinatario() {
            return nomeDestinatario;
        }

        public void setNomeDestinatario(String nomeDestinatario) {
            this.nomeDestinatario = nomeDestinatario;
        }

        public String getEmailDestinatario() {
            return emailDestinatario;
        }

        public void setEmailDestinatario(String emailDestinatario) {
            this.emailDestinatario = emailDestinatario;
        }

    }
    
}
