package br.com.salao.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.salao.entity.Ingredient;
import br.com.salao.entity.Taco;
import br.com.salao.repository.TacoRepositorySpringData;
import br.com.salao.resource.TacoModel;
import br.com.salao.resource.TacoModelAssembler;

@RestController
@RequestMapping(path = "/api/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoControllerRest {
	
	private TacoRepositorySpringData tacoRepo;
	
	private TacoModelAssembler tacoAssembler;
	
	@Autowired
	public DesignTacoControllerRest(TacoRepositorySpringData tacoRepo, TacoModelAssembler tacoAssembler) {	
		this.tacoRepo = tacoRepo;
		this.tacoAssembler = tacoAssembler;
	}
	
	@GetMapping("/recent")
	public CollectionModel<TacoModel> recentTacos(){
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());		
		return tacoAssembler.toCollectionModel(tacoRepo.findAll(page));
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<TacoModel> tacoById(@PathVariable("id") Long id) {
		return tacoRepo.findById(id)
			.map(tacoAssembler::toModel)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());			
	}
	
	
	/*
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		return tacoRepo.findById(id)			
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());			
	}*/
	
		
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		System.out.println(taco);	
		return tacoRepo.save(taco);
	}
	
	@PutMapping(consumes="application/json") 
	@ResponseStatus(HttpStatus.OK)
	public Taco putTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco); 
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<Taco> patchTaco(@PathVariable("id") Long id, @RequestBody Taco path) {
		Optional<Taco> taco_opcional = tacoRepo.findById(id);
		List<Ingredient> ingredients = path.getIngredients();
		
		if(!taco_opcional.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		Taco taco = taco_opcional.get();
		if( path.getCreatedAt() != null ) {
			taco.setCreatedAt(path.getCreatedAt());
		}
		if( path.getName() != null ) {
			taco.setName(path.getName());
		}
		if( ingredients != null ) {
			if( ingredients.size() > 0 ) {
				taco.setIngredients(ingredients);
			}
		}		
		return new ResponseEntity<Taco>(tacoRepo.save(taco), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteTaco(@PathVariable Long id) {
		try {
			tacoRepo.deleteById(id);
		}
		catch( EmptyResultDataAccessException e ) {
			
		}
		catch(DataIntegrityViolationException ex) {
			System.out.println("aaaaaaa");
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex);
			System.out.println(ex.getMessage());
		}
	}
	
}
