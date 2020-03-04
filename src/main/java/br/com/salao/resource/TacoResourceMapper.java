package br.com.salao.resource;

import java.util.ArrayList;
import java.util.Collection;

import br.com.salao.api.DesignTacoControllerRest;
import br.com.salao.entity.Taco;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class TacoResourceMapper {
	
	public static TacoModel toResource(Taco taco) {
		TacoModel resource = new TacoModel();
		resource.add(
			linkTo( 
				methodOn(DesignTacoControllerRest.class)
					.recentTacos()).withSelfRel()
			);
		return resource;
	}
	
	public static Collection<TacoModel> toResourceColletion(Iterable<Taco> tacos) {
		ArrayList<TacoModel> resources = new ArrayList<>();
		for (Taco taco : tacos) {
			resources.add(toResource(taco));
		}
		return resources;
	}
}
