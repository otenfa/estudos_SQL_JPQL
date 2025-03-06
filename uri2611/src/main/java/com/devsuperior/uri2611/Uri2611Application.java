package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dtos.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieMinRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner{
	
	@Autowired
	private MovieMinRepository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<MovieMinProjection> list = movieRepository.searchOne("Action");
		List<MovieMinDTO> resultOne = list.stream().map(x -> new MovieMinDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n*** RESULTADO SQL RAIZ: ");
		for(MovieMinDTO obj : resultOne) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<MovieMinDTO> resultTwo = movieRepository.searchTwo("Action");
		
		System.out.println("\n*** RESULTADO JPQL: ");
		for(MovieMinDTO obj : resultTwo) {
			System.out.println(obj);
		}
		
		
	}
}
