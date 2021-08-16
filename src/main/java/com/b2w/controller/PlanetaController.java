package com.b2w.controller;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.b2w.model.Planeta;
import com.b2w.repository.PlanetaRepository;


@RestController
@RequestMapping("/planetas")
public class PlanetaController {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	
	RestTemplate restTemplate = new RestTemplate();
	Planeta planetas = restTemplate.getForObject("https://swapi.dev/api/planets/", Planeta.class);
	
	
	@GetMapping("/listarPlanetas")
	public List<Planeta> listar() {
		
		return planetaRepository.findAll();

	}

	
	
	@GetMapping("/encontrarPlaneta/{id}")
	public ResponseEntity<Planeta> encontrarPlaneta(@PathVariable @NumberFormat ObjectId id) {

		java.util.Optional<Planeta> planeta = planetaRepository.findById(id);

		if (planeta.isPresent()) {
			return ResponseEntity.ok().body(planeta.get());

		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	@PostMapping("/cadastrarPlaneta")
	@ResponseStatus(HttpStatus.CREATED)
	public Planeta adicionar(@RequestBody Planeta planeta) {
					
		return planetaRepository.save(planeta);
	}
	
	@GetMapping("/removerPlaneta/{id}")
	public ResponseEntity<Planeta> removerPlaneta(@PathVariable @NumberFormat ObjectId id) {
		
		planetaRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}

	
	
	

}
