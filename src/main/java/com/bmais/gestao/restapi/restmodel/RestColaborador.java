package com.bmais.gestao.restapi.restmodel;

import com.bmais.gestao.restapi.mapper.ColaboradorMapper;
import com.bmais.gestao.restapi.model.Colaborador;
import com.bmais.gestao.restapi.model.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class RestColaborador extends RestModel<Colaborador> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "foto")
    private String foto;

    @JsonProperty(value = "data_nascimento")
    private LocalDate dataNascimento;
    
    @JsonProperty(value = "idade")
    private Integer idade;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "celular")
    private String celular;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "data_admissao")
    private LocalDate dataAdmissao;

    @JsonProperty(value = "data_demissao")
    private LocalDate dataDemissao;
    
    @JsonProperty(value = "data_inclusao")
    private LocalDate dataInclusao;

    @JsonProperty(value = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @JsonProperty(value = "status")
    private RestStatusColaborador status;

    @JsonProperty(value = "cpf")
    private String cpf;

    @JsonProperty(value = "rgRne")
    private String rgRne;

    @JsonProperty(value = "emissor")
    private String emissor;

    @JsonProperty(value = "ctps_numero")
    private String ctpsNumero;

    @JsonProperty(value = "ctps_serie")
    private String ctpsSerie;

    @JsonProperty(value = "ctps_uf")
    private RestUf ctpsUf;

    @JsonProperty(value = "pis_pasep")
    private String pisPasep;

    @JsonProperty(value = "empresa")
    private RestEmpresa empresa;

    @JsonProperty(value = "estado_civil")
    private RestEstadoCivil estadoCivil;

    @JsonProperty(value = "sexo")
    private Sexo sexo;

    @JsonProperty(value = "data_expedicao_rg")
    private LocalDate dataExpedicaoRG;

    @JsonProperty(value = "sus")
    private String sus;

    @JsonProperty(value = "reservista")
    private String reservista;

    @JsonProperty(value = "nacionalidade")
    private String nacionalidade;

    @JsonProperty(value = "local_nascimento")
    private String localNascimento;

    @JsonProperty(value = "uf")
    private RestUf uf;

    @JsonProperty(value = "nome_pai")
    private String nomePai;

    @JsonProperty(value = "nome_mae")
    private String nomeMae;

    @JsonProperty(value = "escolaridade")
    private RestEscolaridade escolaridade;

    @JsonProperty(value = "cursando")
    private Boolean cursando;

    @JsonProperty(value = "pcd")
    private Boolean pcd;

    @JsonProperty(value = "nome_emergencia")
    private String nomeEmergencia;

    @JsonProperty(value = "parentesco_emergencia")
    private String parentescoEmergencia;

    @JsonProperty(value = "endereco_emergencia")
    private String enderecoEmergencia;

    @JsonProperty(value = "telefone_emergencia")
    private String telefoneEmergencia;

    @JsonProperty(value = "celular_emergencia")
    private String celularEmergencia;

    @JsonProperty(value = "telefone_comercial_emergencia")
    private String telComercialEmergencia;

    @JsonProperty(value = "titulo_eleitor")
    private String tituloEleitor;

    @JsonProperty(value = "instituicao_ensino")
    private String instituicaoEnsino;

    @JsonProperty(value = "curso")
    private String curso;

    @JsonProperty(value = "observacao")
    private String observacoes;

    @JsonProperty(value = "endereco")
    private RestEndereco endereco;

    @JsonProperty(value = "salario_bruto")
    private BigDecimal salarioBruto;

    @JsonProperty(value = "cliente")
    private RestCliente cliente;

    @JsonProperty(value = "vale_transporte")
    private Boolean valeTransporte;

    @JsonProperty(value = "cargo")
    private RestCargo cargo;

    @JsonProperty(value = "rede_social")
    private String redeSocial;

    @JsonProperty(value = "municipio")
    private RestMunicipio municipio;

    @JsonProperty(value = "motivoDemissao")
    private RestMotivoDemissao motivoDemissao;

    @JsonProperty(value = "menor")
    private Boolean menor;

    @JsonProperty(value = "responsavel")
    private String responsavel;

    @JsonProperty(value = "responsavel_cpf")
    private String responsavelCpf;

    @JsonProperty(value = "horario_seg_qui_inicio")
    private LocalTime horarioSegQuiInicio;

    @JsonProperty(value = "horario_seg_qui_fim")
    private LocalTime horarioSegQuiFim;

    @JsonProperty(value = "horario_sexta_inicio")
    private LocalTime horarioSextaInicio;

    @JsonProperty(value = "horario_sexta_fim")
    private LocalTime horaioSextaFim;

    @JsonProperty(value = "horario_almoco_inicio")
    private LocalTime horarioAlmocoInicio;

    @JsonProperty(value = "horario_almoco_fim")
    private LocalTime horarioAlmocoFim;

    @JsonProperty(value = "operacional")
    private Boolean operacional;

    @JsonProperty(value = "pendencias")
    private Boolean pendencias;

    @JsonProperty(value = "dependentes")
    private Collection<RestDependente> dependentes;

    @JsonProperty(value = "documentos")
    private Collection<RestDocumento> documentos;

    @JsonProperty(value = "cipas")
    private Collection<RestCipa> cipas;

    @JsonProperty(value = "dados_bancarios")
    private RestDadosBancarios dadosBancarios;

    @JsonProperty(value = "evolucoes")
    private Collection<RestEvolucaoColaborador> evolucoes;

    @JsonProperty(value = "ferias")
    private Collection<RestFerias> ferias;

    @JsonProperty(value = "afastamentos")
    private Collection<RestAfastamento> afastamentos;

    @JsonProperty(value = "vales_transporte")
    private Collection<RestValeTransporte> valesTransporte;

    @JsonProperty(value = "colaborador_beneficios")
    private Collection<RestColaboradorBeneficio> colaboradorBeneficios;

    @Override
    public Colaborador restParaModel(){
        Colaborador colaborador = ColaboradorMapper.INSTANCE.convertToModel(this);
        if (colaborador.getDependentes() != null){
            colaborador.getDependentes().forEach(d ->{
                d.setColaborador(colaborador);
                if(d.getColaboradorBeneficios() != null){
                    d.getColaboradorBeneficios().forEach(b -> {
                        b.setColaborador(colaborador);
                        b.setDependente(d);
                    });
                }
            });
        }
        if(colaborador.getDocumentos() != null){
            colaborador.getDocumentos().forEach(d -> d.setColaborador(colaborador));
        }
        if(colaborador.getCipas() != null){
            colaborador.getCipas().forEach(c -> c.setColaborador(colaborador));
        }
        if(this.dadosBancarios != null){
            colaborador.addDadosBancarios(this.dadosBancarios.restParaModel());
        }
        if(colaborador.getEvolucoes() != null){
            colaborador.getEvolucoes().forEach(e -> e.setColaborador(colaborador));
        }
        if(colaborador.getFerias() != null){
            colaborador.getFerias().forEach(f -> f.setColaborador(colaborador));
        }
        if(colaborador.getAfastamentos() != null){
            colaborador.getAfastamentos().forEach(a -> {
                a.setColaborador(colaborador);
                if(a.getDocumentos() != null){
                    a.getDocumentos().forEach(d -> d.setAfastamento(a));
                }
            });
        }
        if(colaborador.getValesTransporte() != null){
            colaborador.getValesTransporte().forEach(v -> v.setColaborador(colaborador));
        }
        if(colaborador.getColaboradorBeneficios() != null){
            colaborador.getColaboradorBeneficios().forEach(b -> b.setColaborador(colaborador));
        }
        return colaborador;
    }



}
