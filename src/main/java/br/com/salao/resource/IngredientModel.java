package br.com.salao.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.salao.entity.Ingredient;
import br.com.salao.entity.Ingredient.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "ingredients")
@JsonInclude(Include.NON_NULL)
public class IngredientModel extends RepresentationModel<IngredientModel>{
	
	private Long id;
	private String codigo;
	private String name;		
	private Type type;
	
	public IngredientModel(Ingredient ingredient) {
		this(
			ingredient.getId(), 
			ingredient.getCodigo(), 
			ingredient.getName(), 
			ingredient.getType());
	}
	
	
	
}
