package com.bmais.gestao.restapi.restmodel;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.bmais.gestao.restapi.model.enums.TipoAgendaPesquisa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestAgendaPesquisa {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoInicial;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate periodoFinal;

    private TipoAgendaPesquisa tipo;

    private String id;
}
