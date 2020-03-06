package br.com.salao.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salao.entity.Ingredient;
import br.com.salao.repository.IngredientRepositorySpringData;
import br.com.salao.resource.IngredientModel;
import br.com.salao.resource.IngredientModelAssembler;

@RestController
@RequestMapping(path = "/api/ingredient", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientControllerRest {

	private IngredientRepositorySpringData ingredientRepo;
	
	private IngredientModelAssembler ingredientAssembler;

	@Autowired
	public IngredientControllerRest(IngredientRepositorySpringData ingredientRepo, 
		IngredientModelAssembler ingredientAssembler) {	
		this.ingredientRepo      = ingredientRepo;
		this.ingredientAssembler = ingredientAssembler;
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<IngredientModel>> allIngredients(){		
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		return new ResponseEntity<>(ingredientAssembler.toCollectionModel(ingredients), HttpStatus.OK);		
	}
	
	/*@GetMapping
	public ResponseEntity<List<Ingredient>> allIngredients(){		
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		List<Ingredient> teste = new ArrayList<>();
		ingredients.forEach(teste::add);		
		return new ResponseEntity<>(teste, HttpStatus.OK);		
	}*/ 
	
	@GetMapping("{id}")	
	public ResponseEntity<IngredientModel> ingredientById(@PathVariable Long id){		
		return ingredientRepo.findById(id)
			.map(ingredientAssembler::toModel)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());		
	}
}
