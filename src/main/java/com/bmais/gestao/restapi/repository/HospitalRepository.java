package com.bmais.gestao.restapi.repository;

import java.util.Collection;
import java.util.Set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bmais.gestao.restapi.model.Hospital;
import com.bmais.gestao.restapi.repository.custom.HospitalRepositoryCustom;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long>, HospitalRepositoryCustom {

    @Query("select new Hospital(h.id, h.razaoSocial) from Hospital h join h.status s where s.id = 1 order by h.razaoSocial")
    Collection<Hospital> listaSimples();

    Boolean existsByRazaoSocial(String razao);

    Boolean existsByCnpj(String cnpj);

    @Modifying
    @Query("update Hospital h set h.status.id = 2L where h.id = :id")
    void desativarHospital(Long id);

    @Query("select new Hospital(h.id, h.razaoSocial) from Hospital h where h.id = :id")
    Hospital internacaoHospital(Long id);

    @Query("select new Hospital(h.id, h.razaoSocial) from Hospital h where h = :hospital")
    Hospital getHospital(Hospital hospital);
    
    @Query(value = "select new Hospital(h.id, h.razaoSocial) from Vinculo v join v.hospital h where h.status.id = 1 and v.cliente.id = :cliente order by h.razaoSocial")
    Set<Hospital> pesquisarHospitaisVinculos(Long cliente);

    @Query("select h.id from Internacao i join i.hospital h where i.id = :id")
    Long hospitalInternacao(Long id);
}
