//package com.bmais.gestao.restapi.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.bmais.gestao.restapi.utility.EnviaEmail;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//
//
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories("com.bmais.gestao.restapi")
//public class RestAPIConfig {
//
//    @Bean
//    public EnviaEmail enviaEmail(){
//       return new EnviaEmail();
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//          .apiInfo(apiInfo())
//          .securityContexts(Arrays.asList(securityContext()))
//          .securitySchemes(Arrays.asList(apiKey()))
//          .select()
//          .apis(RequestHandlerSelectors.any())
//          .paths(PathSelectors.any())
//          .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//          "Encarte Digital API",
//          null,
//          "1.0",
//          null,
//          new Contact("Luxfacta Solucoes de TI", "luxfacta.com", "comercial.br@luxfacta.com"),
//          null,
//          null,
//          Collections.emptyList());
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//}
