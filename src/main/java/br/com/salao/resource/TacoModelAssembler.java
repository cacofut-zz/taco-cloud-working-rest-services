package br.com.salao.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.salao.api.DesignTacoControllerRest;
import br.com.salao.entity.Taco;

@Component
public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel>{
	
	private static final IngredientModelAssembler 
		ingredientAssembler = new IngredientModelAssembler();
		
	public TacoModelAssembler() {
		super(DesignTacoControllerRest.class, TacoModel.class);
	}

	@Override
	public TacoModel toModel(Taco taco) {		
		TacoModel resource = createModelWithId(taco.getId(), taco, taco.getId());
		resource.setName(taco.getName());
		resource.setCreatedAt(taco.getCreatedAt());
		resource.setIngredients(ingredientAssembler.toCollectionModel(taco.getIngredients()));
		return resource;
	}
		
	@Override
	public CollectionModel<TacoModel> toCollectionModel(Iterable<? extends Taco> entities) {
		CollectionModel<TacoModel> resources = super.toCollectionModel(entities);
		resources.add(linkTo(methodOn(DesignTacoControllerRest.class).recentTacos()).withSelfRel());
		return super.toCollectionModel(entities);
	}
	
}
