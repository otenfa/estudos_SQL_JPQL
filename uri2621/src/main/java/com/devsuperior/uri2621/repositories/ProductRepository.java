package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT p.name "
			+ "FROM products p "
			+ "INNER JOIN providers v ON p.id_providers = v.id "
			+ "WHERE UPPER(v.name) LIKE UPPER(CONCAT(:beginName,'%')) "
			+ "AND (p.amount > :limInf AND p.amount < :limSup) "
			+ "")
	List<ProductMinProjection> searchOne(String beginName, Integer limInf, Integer limSup);

	@Query("SELECT new com.devsuperior.uri2621.dtos.ProductMinDTO(obj.name) "
			+ "FROM Product obj "
			+ "WHERE UPPER(obj.provider.name) LIKE UPPER(CONCAT(:beginName,'%')) "
			+ "AND obj.amount BETWEEN :limInf AND :limSup "
			+ "")
	List<ProductMinDTO> searchTwo(String beginName, Integer limInf, Integer limSup);
	
}
