//package com.bmais.gestao.restapi.repository.custom;
//
//import com.bmais.gestao.restapi.model.*;
//import com.bmais.gestao.restapi.model.enums.TipoHistorico;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.*;
//import java.time.LocalDateTime;
//import java.util.Collection;
//
//public class HistoricoCapeanteRepositoryCustomImpl extends Repository<HistoricoCapeante> implements HistoricoCapeanteRepositoryCustom {
//
//
//    @Override
//    public Collection<HistoricoCapeante> listarHistoricoCapeante(Long idCapeante) {
//        CriteriaQuery<HistoricoCapeante> criteria = super.getCriteria();
//        Root<HistoricoCapeante> root = criteria.from(getClazz());
//        Predicate conjunction = builder().conjunction();
//
//        Join<HistoricoCapeante, Usuario> joinUsuario = root.join(HistoricoCapeante_.USUARIO);
//
//        Path<String> idHistoricoCapeante = root.get(HistoricoCapeante_.ID);
//        Path<Long> idUsuario = joinUsuario.get(Usuario_.ID);
//        Path<String> emailUsuario = joinUsuario.get(Usuario_.EMAIL);
//        Path<TipoHistorico> tipoHistorico = root.get(HistoricoCapeante_.TIPO_HISTORICO);
//        Path<LocalDateTime> data = root.get(HistoricoCapeante_.DATA);
//
//        criteria.multiselect(idHistoricoCapeante, idUsuario, emailUsuario, tipoHistorico, data);
//
//        if (idCapeante != null) {
//            Predicate equal = builder().equal(root.get(HistoricoCapeante_.CAPEANTE).get(Capeante_.ID), idCapeante);
//            conjunction = builder().and(conjunction, equal);
//        }
//
//        criteria.where(conjunction);
//        TypedQuery<HistoricoCapeante> typedQuery = entityManager.createQuery(criteria);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public Class<HistoricoCapeante> getClazz() {
//        return HistoricoCapeante.class;
//    }
//}
