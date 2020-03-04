package br.com.salao.resource;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import br.com.salao.entity.Ingredient;

public class TacoResource extends RepresentationModel<TacoResource>{
		
	private final String name;
	private final Date createdAt;
	private final List<Ingredient> ingredients;
		
	public TacoResource(String name, Date createdAt, List<Ingredient> ingredients) {				
		this.name = name;
		this.createdAt = createdAt;
		this.ingredients = ingredients;		 
	}


	public String getName() {
		return name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	
	
}
