package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ProductMinProjection> list = repository.searchOne("p", 10, 20);
		List<ProductMinDTO> resultOne = list.stream().map(x -> new ProductMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n RESULT QUERY USING JPA ");
		for(ProductMinDTO obj : resultOne) {
			System.out.println(obj);
		}
		
		System.out.println("\n\n");
	
		List<ProductMinDTO> resultTwo = repository.searchTwo("p", 10, 20);
		
		System.out.println("\n RESULT QUERY USING JPQL ");
		for(ProductMinDTO obj : resultTwo) {
			System.out.println(obj);
		}

	}
}
