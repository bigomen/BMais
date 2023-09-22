package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.ColaboradorBeneficio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ColaboradorBeneficioRepository extends CrudRepository<ColaboradorBeneficio, Long> {

    @Query("select new ColaboradorBeneficio(c.id, b.id, b.descricao, c.dataAdesao, c.dataFim, c.valorOferecido, c.valorContratado, c.valorDescontado, c.observacao) " +
            "from ColaboradorBeneficio c join c.beneficio b join c.dependente d where d.id = :id order by d.nome")
    Collection<ColaboradorBeneficio> beneficiosDependentes(Long id);

    @Query("select new ColaboradorBeneficio(c.id, b.id, b.descricao, c.dataAdesao, c.dataFim, c.valorOferecido, c.valorContratado, c.valorDescontado, c.observacao) " +
            "from ColaboradorBeneficio c join c.beneficio b where c.colaborador.id = :id and c.dependente.id = null order by c.dataAdesao")
    Collection<ColaboradorBeneficio> beneficiosColaborador(Long id);
}
