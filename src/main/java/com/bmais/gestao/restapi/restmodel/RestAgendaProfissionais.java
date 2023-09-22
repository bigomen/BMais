//package com.bmais.gestao.restapi.restmodel;
//
//import com.bmais.gestao.restapi.mapper.AgendaProfissionaisMapper;
//import com.bmais.gestao.restapi.model.AgendaProfissionais;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//
//@Getter
//@Setter
//@NoArgsConstructor
//public class RestAgendaProfissionais extends RestModel<AgendaProfissionais> implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @JsonProperty(value = "servico")
//    private RestServico servico;
//
//    @JsonProperty(value = "cliente")
//    private RestCliente cliente;
//
//    @JsonProperty(value = "hospital")
//    private RestHospital hospital;
//
//    @JsonProperty(value = "medico")
//    private RestAuditor medico;
//
//    @JsonProperty(value = "enfermeiro")
//    private RestAuditor enfermeiro;
//
//    @JsonProperty(value = "colaborador")
//    private RestColaborador colaborador;
//
//    @Override
//    public AgendaProfissionais restParaModel(){
//        return AgendaProfissionaisMapper.INSTANCE.convertToModel(this);
//    }
//}
