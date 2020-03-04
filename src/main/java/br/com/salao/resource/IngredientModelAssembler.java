package br.com.salao.resource;

import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.salao.api.IngredientControllerRest;
import br.com.salao.entity.Ingredient;

@Component
public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel>{

	public IngredientModelAssembler() {
		super(IngredientControllerRest.class, IngredientModel.class);
	}
	
	@Override
	public IngredientModel toModel(Ingredient ingredient) {		
		IngredientModel resource = createModelWithId(ingredient.getId(), ingredient, ingredient.getId());
		resource.setName(ingredient.getName());
		resource.setType(ingredient.getType());
		resource.setId(ingredient.getId());
		resource.setCodigo(ingredient.getCodigo());
		return resource;
	}

	@Override
	public CollectionModel<IngredientModel> toCollectionModel(Iterable<? extends Ingredient> entities){
		CollectionModel<IngredientModel> resources = super.toCollectionModel(entities);
		resources.add(linkTo(methodOn(IngredientControllerRest.class).allIngredients()).withSelfRel());
		return resources;
	}
}
