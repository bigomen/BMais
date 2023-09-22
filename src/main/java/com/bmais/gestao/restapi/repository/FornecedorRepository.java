package com.bmais.gestao.restapi.repository;

import com.bmais.gestao.restapi.model.Fornecedor;
import com.bmais.gestao.restapi.repository.custom.FornecedorRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Long>, FornecedorRepositoryCustom {

    @Query("select new Fornecedor(f.id) from Fornecedor f join f.status s where s.id = 1L and f.razaoSocial = :razao")
    Fornecedor findByRazaoSocial(String razao);

    @Query("select new Fornecedor(f.id) from Fornecedor f join f.status s where s.id = 1L and f.cnpj = :cnpj")
    Fornecedor findByCnpj(String cnpj);

    @Query("select CASE WHEN count(f.id) > 0 THEN true ELSE false END from Fornecedor f where (f.razaoSocial = :razaoSocial or f.cnpj = :cnpj) and f.id <> :id")
    Boolean validarRazaoCnpj(String razaoSocial, String cnpj, Long id);

}
