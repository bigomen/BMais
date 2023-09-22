package com.bmais.gestao.restapi.restmodel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import com.bmais.gestao.restapi.constants.Constantes;
import com.bmais.gestao.restapi.mapper.AuditorMapper;
import com.bmais.gestao.restapi.model.Auditor;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestAuditor  extends RestModel<Auditor> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "assinatura")
    private String assinatura;

    @NotBlank(message = "Obrigatório informar o nome do auditor")
    @Size(max = 60, message = "Nome do auditor deve ter no máximo 60 caracteres")
    @JsonProperty(value = "nome")
    private String nome;

    @NotNull(message = "Obrigatório informar a data de nascimento do auditor")
    @Past(message = "Data de nascimento inválida")
    @JsonProperty(value = "data_nascimento")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private LocalDate dataNascimento;

    @NotBlank(message = "Obrigatório informar o CRM-COREN")
    @Size(max = 20, message = "NDeve ter no máximo 20 caracteres")
    @JsonProperty(value = "crm_corem")
    private String crmCorem;

    @NotBlank(message = "CPF Inválido")
    @CPF(message = "CPF Inválido")
    @JsonProperty(value = "cpf")
    private String cpf;

    @NotBlank(message = "RG Inválido")
    @Size(max = 9, message = "RG deve ter no máximo 9 dígitos")
    @JsonProperty(value = "rg")
    private String rg;

    @JsonProperty(value = "emissor")
    private String emissor;

    @Valid
    @NotNull(message = "Obrigatório informar o endereço do auditor")
    @JsonProperty(value = "endereco")
    private RestEndereco endereco;

    @NotNull(message = "Obrigatório informar o tipo do auditor")
    @JsonProperty(value = "tipoAuditor")
    private RestTipoAuditor tipoAuditor;

    @JsonProperty(value = "sexo")
    private Sexo sexo;

    @JsonProperty(value = "status")
    private RestStatusAuditor status;

    @JsonProperty(value = "coberturas")
    private Collection<RestCobertura> coberturas;

    @NotBlank(message = "Obrigatório informar um email válido")
    @Email(message = "Email Inválido")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "Obrigatório informar o telefone do auditor")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 dígitos")
    @JsonProperty(value = "telefone")
    private String telefone;

    @NotNull(message = "Obrigatório informar o prestador que o auditor está vinculado")
    @JsonProperty(value = "prestador")
    private RestPrestador prestador;

    @Valid
    @JsonProperty(value = "vinculos")
    private Collection<RestVinculo> vinculos;

    @Valid
    @JsonProperty(value = "documentos")
    private Collection<RestDocumento> documentos;

    @JsonProperty(value = "usuario")
    private RestUsuario usuario;

    @Override
    public Auditor restParaModel()
    {
        Auditor auditor = AuditorMapper.INSTANCE.convertToModel(this);
        if(Objects.nonNull(auditor.getVinculos())){
            auditor.getVinculos().forEach(v -> v.setAuditor(auditor));
        }
        return auditor;
    }

}
