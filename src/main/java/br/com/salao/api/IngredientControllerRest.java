package br.com.salao.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
<<<<<<< HEAD
	@GetMapping
=======
	/*
	/*@GetMapping
>>>>>>> 5aecc4d404b778ce455e9b07da3c5fc2ceff992b
	public ResponseEntity<CollectionModel<IngredientModel>> allIngredients(){		
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();		
		return new ResponseEntity<>(ingredientAssembler.toCollectionModel(ingredients), HttpStatus.OK);		
	}
	
<<<<<<< HEAD
	/*
=======

>>>>>>> 5aecc4d404b778ce455e9b07da3c5fc2ceff992b
	@GetMapping
	public ResponseEntity<List<Ingredient>> allIngredients(){		
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		List<Ingredient> teste = new ArrayList<>();
		ingredients.forEach(teste::add);		
		return new ResponseEntity<>(teste, HttpStatus.OK);		
<<<<<<< HEAD
	} */
=======

	}
	
	
	/*
	@GetMapping 
	public ResponseEntity<CollectionModelList> allIngredients(){		
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		CollectionModelList colection = new CollectionModelList();
		colection.setIngredients(ingredientAssembler.toCollectionModel(ingredients));
		return new ResponseEntity<>(colection, HttpStatus.OK);		
	}*/
	
	
	/*@GetMapping 
	public ResponseEntity<CollectionModel<IngredientModel>> allIngredients(){		
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();		
		return new ResponseEntity<>(ingredientAssembler.toCollectionModel(ingredients), HttpStatus.OK);		
	}*/
>>>>>>> 5aecc4d404b778ce455e9b07da3c5fc2ceff992b
	
	
	@GetMapping("{id}")	
	public ResponseEntity<Ingredient> ingredientById(@PathVariable Long id){		
		return ingredientRepo.findById(id)			
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());		
	}
	
	/*@GetMapping("{id}")	
	public ResponseEntity<IngredientModel> ingredientById(@PathVariable Long id){		
		return ingredientRepo.findById(id)
			.map(ingredientAssembler::toModel)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());		
	}*/
	
	@PutMapping(consumes = "application/json")
	public ResponseEntity<Ingredient> putIngredient(@RequestBody Ingredient ingredient){
		return new ResponseEntity<>(ingredientRepo.save(ingredient), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient){
		return new ResponseEntity<>(ingredientRepo.save(ingredient), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
		try {
			ingredientRepo.deleteById(id);		
			return ResponseEntity.ok().build();
		}
		catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
		}
		catch(DataIntegrityViolationException e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<Ingredient> patchIngredient(@PathVariable Long id, @RequestBody Ingredient patch){
		Optional<Ingredient> ingredient_opcional = ingredientRepo.findById(id);
		if(!ingredient_opcional.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		Ingredient ingredient = ingredient_opcional.get();
		if(patch.getCodigo() != null) {
			ingredient.setCodigo(patch.getCodigo());
		}		
		if(patch.getName() != null) {
			ingredient.setName(patch.getName());
		}
		if(patch.getType() != null) {
			ingredient.setType(patch.getType());
		}
		return new ResponseEntity<>(ingredientRepo.save(ingredient), HttpStatus.OK);
	}
}
