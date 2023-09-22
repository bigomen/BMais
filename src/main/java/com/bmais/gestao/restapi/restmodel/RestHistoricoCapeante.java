//package com.bmais.gestao.restapi.restmodel;
//
//import com.bmais.gestao.restapi.mapper.HistoricoCapeanteMapper;
//import com.bmais.gestao.restapi.model.HistoricoCapeante;
//import com.bmais.gestao.restapi.model.enums.TipoHistorico;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
//public class RestHistoricoCapeante extends RestModel<HistoricoCapeante> implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @JsonProperty(value = "capeante")
//    private RestCapeante capeante;
//
//    @JsonProperty(value = "usuario")
//    private RestUsuario usuario;
//
//    @JsonProperty(value = "tipo_historico")
//    private TipoHistorico tipoHistorico;
//
//    @JsonProperty(value = "data")
//    private LocalDateTime data;
//
//    @Override
//    public HistoricoCapeante restParaModel() {
//        return HistoricoCapeanteMapper.INSTANCE.convertToModel(this);
//    }
//}
