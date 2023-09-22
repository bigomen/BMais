package com.bmais.gestao.restapi.service;

import com.bmais.gestao.restapi.config.EmailConfig;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.constants.MensagensID;
import com.bmais.gestao.restapi.exceptions.IncorrectData;
import com.bmais.gestao.restapi.exceptions.MandatoryAttribute;
import com.bmais.gestao.restapi.exceptions.ObjectAlreadyInBase;
import com.bmais.gestao.restapi.exceptions.ObjectNotFoundException;
import com.bmais.gestao.restapi.model.GrupoUsuario;
import com.bmais.gestao.restapi.model.StatusUsuario;
import com.bmais.gestao.restapi.model.Usuario;
import com.bmais.gestao.restapi.repository.ClienteRepository;
import com.bmais.gestao.restapi.repository.UsuarioRepository;
import com.bmais.gestao.restapi.restmodel.RestRecuperacaoSenha;
import com.bmais.gestao.restapi.restmodel.RestUsuario;
import com.bmais.gestao.restapi.restmodel.RestUsuarioPesquisa;
import com.bmais.gestao.restapi.utility.UtilSecurity;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UsuarioService extends com.bmais.gestao.restapi.service.Service<Usuario, RestUsuario>{

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailConfig emailConfig;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailConfig emailConfig) {
        super();
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailConfig = emailConfig;
    }

    public Collection<RestUsuario> lista(RestUsuarioPesquisa params){
        Collection<Usuario> usuarios = usuarioRepository.lista(params);
        return usuarios.stream().map(Usuario::modelParaRest).collect(Collectors.toList());
    }

    public Collection<RestUsuario> listaSimples(){
        Collection<Usuario> usuarios = usuarioRepository.listaSimples(StatusUsuario.ATIVO);
        return usuarios.stream().map(Usuario::modelParaRest).collect(Collectors.toList());
    }

    public RestUsuario detalhes(String id){
        if(!usuarioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        Usuario usuario = usuarioRepository.detalhes(UtilSecurity.decryptId(id));
        if(usuario.getGrupo().getId() == GrupoUsuario.CLIENTE){
            usuario.setCliente(clienteRepository.getClienteUsuario(usuario.getId()));
        }
        return usuario.modelParaRest();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void novo(RestUsuario restUsuario) throws MessagingException {
        if(usuarioRepository.existsByEmail(restUsuario.getEmail(), StatusUsuario.ATIVO)){
            throw new ObjectAlreadyInBase(MensagensID.USU + restUsuario.getEmail() + MensagensID.JaCadastrado);
        }
        if(restUsuario.getGrupo() == null){
            throw new MandatoryAttribute("Perfil");
        }
        Usuario usuario = restUsuario.restParaModel();
        if(usuario.getGrupo().getId() == GrupoUsuario.CLIENTE){
            if(usuario.getCliente().getId() == null){
                throw new MandatoryAttribute("Cliente");
            }
            if(!clienteRepository.existsById(usuario.getCliente().getId())) throw new IncorrectData("Cliente não encontrado");

        }else {
            if(Objects.isNull(restUsuario.getEmpresas())) throw new MandatoryAttribute("Empresa é obrigatorio");
        }
        usuario.setResetPasswordToken(RandomString.make(45));
        usuarioRepository.save(usuario);
        sendSmtp(usuario.getEmail(), usuario.getResetPasswordToken());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long novoUsuarioAuditor(Usuario usuario) throws MessagingException {
        usuario.setResetPasswordToken(RandomString.make(45));
        usuarioRepository.save(usuario);
        sendSmtp(usuario.getEmail(), usuario.getResetPasswordToken());
        return usuario.getId();
    }

    public Boolean validarEmailUsuarioAuditor(String email){
        return usuarioRepository.existsByEmail(email, StatusUsuario.ATIVO);
    }

    public Boolean validarEmailUsuarioAuditor(String email, Long id){
        return usuarioRepository.existsByEmail(email, StatusUsuario.ATIVO, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(RestUsuario restUsuario, String id){
        if(usuarioRepository.getUsuarioStatus(UtilSecurity.decryptId(id)) == StatusUsuario.EXCLUIDO){
            throw new IncorrectData("Não é possivel editar, usuario excluido");
        }
        if(!usuarioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        if(usuarioRepository.validarUpdate(restUsuario.getEmail(), UtilSecurity.decryptId(id))){
            throw new ObjectAlreadyInBase("Email já utilizado por outro usuario");
        }
        if(restUsuario.getGrupo() == null){
            throw new MandatoryAttribute("Perfil");
        }
        restUsuario.setId(id);
        Usuario usuario = restUsuario.restParaModel();
        if(usuario.getGrupo().getId() == GrupoUsuario.CLIENTE){
            if(usuario.getCliente().getId() == null){
                throw new MandatoryAttribute("Cliente");
            }
            if(!clienteRepository.existsById(usuario.getCliente().getId())) throw new IncorrectData("Cliente não encontrado");
        }else {
            if(Objects.isNull(restUsuario.getEmpresas())) throw new MandatoryAttribute("Empresa é obrigatorio");
        }
        usuario.setSenha(usuarioRepository.getSenha(usuario.getId()));
        usuarioRepository.save(usuario);
    }

    private void criarSenha(Usuario usuario){

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void apagar(String id){
        if(!usuarioRepository.existsById(UtilSecurity.decryptId(id))){
            throw new ObjectNotFoundException(MensagensID.CadastroNaoEncontrado);
        }
        if(usuarioRepository.getUsuarioStatus(UtilSecurity.decryptId(id)) == StatusUsuario.EXCLUIDO){
            throw new IncorrectData("Usuario já excluido");
        }
        usuarioRepository.desativar(UtilSecurity.decryptId(id), id + Constantes.EMAIL_USUARIO_DESATIVADO);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateResetPasswordToken(String email) throws MessagingException {
        if(usuarioRepository.validarUsuarioByEmail(email)){
            String token = RandomString.make(45);
            usuarioRepository.setPassToken(token, email);
            sendSmtp(email, token);
        }else{
            throw new ObjectNotFoundException(MensagensID.UsuarioNaoEncontrado);
        }
    }

//    private void sendMail(String email, String token) throws MessagingException {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        MimeMessage message = javaMailSender.createMimeMessage();
//        message.setSubject(MensagensID.CabecalhoEmail);
//        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
//        javaMailSender.setHost(emailConfig.getHost());
//        javaMailSender.setPort(emailConfig.getPorta());
//        javaMailSender.setUsername(emailConfig.getUsuario());
//        javaMailSender.setPassword(emailConfig.getSenha());
//        Properties properties = new Properties();
//        properties.setProperty(emailConfig.getAutenticacao(), emailConfig.getAuthHabilitado());
//        properties.setProperty(emailConfig.getTls(), emailConfig.getTlsHabilitado());
//        properties.setProperty("mail.transport.protocol", "smtp");
//        properties.setProperty("mail.debug", "true");
//        properties.setProperty("mail.smtp.socketFactory.port", emailConfig.getPorta().toString());
//        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
//        javaMailSender.setJavaMailProperties(properties);
//        helper.setFrom(emailConfig.getUsuario());
//        helper.setTo(email);
//        helper.setText(MensagensID.CorpoEmail + token + MensagensID.BotaoEmail, true);
//        javaMailSender.send(message);
//    }

    private void sendSmtp(String email, String token) throws MessagingException{
        final Authenticator auth = new SMTPAuthenticator(emailConfig.getUsuario(), emailConfig.getSenha());
        final Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", emailConfig.getHost());
        props.setProperty("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session mailSession = Session.getInstance(props, auth);
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(emailConfig.getAutor(), false));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email, false));
        msg.setSubject("B+ - Solicitação de nova senha ");
        msg.setSentDate(new Date());
        msg.addHeader("Content-type", "text/html; charset=utf-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setContent("<font style=\"font-family:Arial;font-size:11pt\">Ol&aacute;Tudo bem?<br><br>Usuario criado com sucesso, clique no link abaixo para cadastrar uma nova senha.<br><br><a href=\"https://bmais.luxfacta.com/recover-password/"+ token +"\">Clique aqui</a> para proceder com a senha nova.<br><br>Se voc&ecirc; n&atilde;o solicitou o reset da sua senha, por favor nos informe respondendo esse email.<br><br>Obrigado,<br><br><b>Suporte B+</b><br>https://bmais.luxfacta.com</font>",
                "text/html; charset=utf-8");
        Transport.send(msg);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void recuperar(RestRecuperacaoSenha restRecuperacaoSenha){
        if(restRecuperacaoSenha.getToken() == null || restRecuperacaoSenha.getSenha() == null){
            throw new ObjectNotFoundException(MensagensID.UsuarioNaoEncontrado);
        }
        if(usuarioRepository.existsByResetPasswordToken(restRecuperacaoSenha.getToken())){
            updatePassword(restRecuperacaoSenha.getSenha(), restRecuperacaoSenha.getToken());
        }else {
            throw new ObjectNotFoundException(MensagensID.UsuarioNaoEncontrado);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void updatePassword(String newPassword, String token){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuarioRepository.setNovaSenha(passwordEncoder.encode(newPassword), token, null);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void desativarUsuariosPrestador(Collection<Long> userId){
        for(Long id : userId){
            usuarioRepository.inativarUsuario(id, StatusUsuario.INATIVO);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setStatusUsuarioAuditor(Long userId, Long status){
        usuarioRepository.atualizarStatusUsuarioAuditor(userId, status);
    }

    private static class SMTPAuthenticator extends Authenticator
    {
        String username = null;
        String password = null;

        public SMTPAuthenticator(String username, String password) {
            super();
            this.password=password;
            this.username=username;

        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    @Override
    protected CrudRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }
}
