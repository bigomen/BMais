package com.bmais.gestao.restapi.config;

import com.bmais.gestao.restapi.utility.EnviaEmail;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Locale;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:messages.properties")
@EnableJpaRepositories("com.bmais.gestao.restapi")
@EnableAutoConfiguration
@EnableScheduling
public class BBConfig
{

	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource)
	{
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource);
		return validatorFactoryBean;
	}
	
	@Bean
	public LocaleResolver localeResolver() 
	{
	   AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
	   localeResolver.setDefaultLocale(new Locale("pt", "BR"));
	   return localeResolver;
	}

	@Bean
	public EnviaEmail enviaEmail()
	{
		return new EnviaEmail();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JavaMailSender javaMailSender(){
		return new JavaMailSender() {
			@Override
			public MimeMessage createMimeMessage() {
				return null;
			}

			@Override
			public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
				return null;
			}

			@Override
			public void send(MimeMessage mimeMessage) throws MailException {

			}

			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {

			}

			@Override
			public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

			}

			@Override
			public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

			}

			@Override
			public void send(SimpleMailMessage simpleMailMessage) throws MailException {

			}

			@Override
			public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

			}
		};
	}

}
