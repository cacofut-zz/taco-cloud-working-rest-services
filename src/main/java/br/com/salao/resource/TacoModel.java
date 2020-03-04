package br.com.salao.resource;

import java.util.Date;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@Relation(collectionRelation = "tacos")
@JsonInclude(Include.NON_NULL)
public class TacoModel extends RepresentationModel<TacoModel>{
		
	private String name;
	private Date createdAt;
	private CollectionModel<IngredientModel> ingredients;


	
}
