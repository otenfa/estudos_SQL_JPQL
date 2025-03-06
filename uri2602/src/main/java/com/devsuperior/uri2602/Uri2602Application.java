package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dtos.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	
	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<CustomerMinProjection> list = repository.searchOne("Rj");
				
		System.out.println("\n *** RESULTADO SQL RAIZ (SEM .DTO) *** ");
		for(CustomerMinProjection obj : list) {
			System.out.println(obj.getName());
		}

		System.out.println("\n *** RESULTADO SQL RAIZ *** ");
		List<CustomerMinDTO> resultOne = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		for (CustomerMinDTO obj : resultOne) {
			System.out.println(obj);
		}
		
		System.out.println("\n *** RESULTADO JPQL *** ");
		List<CustomerMinDTO> resultTwo = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		for (CustomerMinDTO obj : resultTwo) {
			System.out.println(obj);
		}
		
	}
}
