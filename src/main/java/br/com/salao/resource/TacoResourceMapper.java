package br.com.salao.resource;

import java.util.ArrayList;
import java.util.Collection;

import br.com.salao.api.DesignTacoControllerAPI;
import br.com.salao.entity.Taco;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class TacoResourceMapper {
	
	public static TacoResource toResource(Taco taco) {
		TacoResource resource = new TacoResource(			
			taco.getName(), 
			taco.getCreatedAt(),
			taco.getIngredients()
		);
		resource.add(
			linkTo( 
				methodOn(DesignTacoControllerAPI.class)
					.recentTacos()).withSelfRel()
			);
		return resource;
	}
	
	public static Collection<TacoResource> toResourceColletion(Iterable<Taco> tacos) {
		ArrayList<TacoResource> resources = new ArrayList<>();
		for (Taco taco : tacos) {
			resources.add(toResource(taco));
		}
		return resources;
	}
}
