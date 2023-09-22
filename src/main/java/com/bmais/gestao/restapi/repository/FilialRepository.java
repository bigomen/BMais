//package com.bmais.gestao.restapi.repository;
//
//import com.bmais.gestao.restapi.model.Filial;
//import com.bmais.gestao.restapi.repository.custom.FilialRepositoryCustom;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface FilialRepository extends CrudRepository<Filial, Long>, FilialRepositoryCustom {
//
//    @Query(value = "select f from Filial f join fetch f.empresa e join fetch f.status s where f.id = :id")
//    Optional<Filial> findById(@Param(value = "id") Long id);
//}
