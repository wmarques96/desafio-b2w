package com.b2w;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.b2w.controller.PlanetaController;
import com.b2w.model.Planeta;
import com.b2w.repository.PlanetaRepository;


@SpringBootTest
class DesafioStarWarsApplicationTests {


	@Autowired
	PlanetaController controller;
    
    @Autowired
    PlanetaRepository repo ;
    
    @AfterEach
    public void limparDados() { 	
    	repo.deleteAll();
    }
    

	@Test
	final void testAdicionar() {
		Planeta planeta = new Planeta();
		planeta.setNome("jupiter");
		
		controller.adicionar(planeta);
		assertNotNull(repo.findById(planeta.getId()));
	}

	@Test 
	final void testListar() {
		
		Planeta planeta = new Planeta();
		planeta.setNome("venus");
	
		planeta = repo.save(planeta);
		
		List<Planeta> list = controller.listar();
		assertThat(list).size().isEqualTo(1); 
		
	}

	@Test
	final void testEncontrarPlaneta() {
		Planeta planeta = new Planeta();
		planeta.setNome("marte");
	
		planeta = repo.save(planeta);
		  
		Planeta planetaEncontrado = controller.encontrarPlaneta(planeta.getId()).getBody();
	   
	   assertEquals("marte", planetaEncontrado.getNome());
	}
	
	

}
