package com.bmais.gestao.restapi.utility;

import java.util.Collection;

import com.bmais.gestao.restapi.security.VinculoFiltro;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseRuleUtil {

    @JsonIgnore
    Collection<VinculoFiltro> vinculos = null;

    @JsonIgnore
    Long clienteId = null;

    public Collection<VinculoFiltro> getVinculos() {
        return vinculos;
    }

    public void setVinculos(Collection<VinculoFiltro> vinculos) {
        this.vinculos = vinculos;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
