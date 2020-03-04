package br.com.salao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.salao.entity.Taco;
import br.com.salao.repository.TacoRepositorySpringData;
import br.com.salao.resource.TacoModelAssembler;

//@RepositoryRestController
public class RecentTacosController {

private TacoRepositorySpringData tacoRepo;
	
	private TacoModelAssembler tacoAssembler;
	
	@Autowired
	public RecentTacosController(TacoRepositorySpringData tacoRepo, TacoModelAssembler tacoAssembler) {	
		this.tacoRepo = tacoRepo;
		this.tacoAssembler = tacoAssembler;
	}
	
	@GetMapping(path = "/tacoss/recent", produces = "application/json")
	public ResponseEntity<Taco> recentTacos(){		
		//PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		//CollectionModel<TacoModel> resources = tacoAssembler.toCollectionModel(tacoRepo.findAll(page));
		//return tacoAssembler.toCollectionModel(tacoRepo.findAll(page));
		return new ResponseEntity<>(new Taco(), HttpStatus.OK);
	}
}
