package br.com.salao.resource;

import java.util.ArrayList;

import org.springframework.hateoas.CollectionModel;

public class CollectionModelList {

	private CollectionModel<IngredientModel> ingredientsaa;

	public CollectionModelList() {	
		this.ingredientsaa = new CollectionModel<IngredientModel>(new ArrayList<>());
	}

	public CollectionModel<IngredientModel> getIngredients() {
		return ingredientsaa;
	}

	public void setIngredients(CollectionModel<IngredientModel> ingredients) {
		this.ingredientsaa = ingredients;
	}

	
}
