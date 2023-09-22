package com.bmais.gestao.restapi.repository.specification;

import com.bmais.gestao.restapi.model.DadosBancarios;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@NoArgsConstructor
@AllArgsConstructor
public class DadosBancariosSpecification implements Specification<DadosBancarios>
{
    private Long banco;
    private String agencia;
    private String conta;

    @Override
    public Predicate toPredicate(Root<DadosBancarios> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
    {
        Predicate conjunction = criteriaBuilder.conjunction();

//        if(banco != null)
//        {
//            Predicate equalBanco = criteriaBuilder.equal(root.get(DadosBancarios_.BANCO).get(Banco_.ID), banco);
//            conjunction = criteriaBuilder.and(conjunction, equalBanco);
//        }
//
//        if(StringUtils.isNotBlank(agencia))
//        {
//            Predicate equalAgencia = criteriaBuilder.equal(root.get(DadosBancarios_.AGENCIA), agencia);
//            conjunction = criteriaBuilder.and(conjunction, equalAgencia);
//        }
//
//        if(StringUtils.isNotBlank(conta))
//        {
//            Predicate equalConta = criteriaBuilder.equal(root.get(DadosBancarios_.CONTA), conta);
//            conjunction = criteriaBuilder.and(conjunction, equalConta);
//        }

        return conjunction;
    }
}
