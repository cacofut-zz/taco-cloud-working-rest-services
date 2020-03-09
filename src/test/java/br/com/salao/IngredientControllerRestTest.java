package br.com.salao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.salao.entity.Ingredient;
import br.com.salao.resource.CollectionModelList;
import br.com.salao.resource.IngredientModel;



//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IngredientControllerRestTest {

	@LocalServerPort
	int randomServerPort;
	
	@Test
	void test_ingredients_api_by_id() {
		final String baseUrl = "http://localhost:8080/api/ingredient/{id}";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ingredient> ingredient = 
				restTemplate.getForEntity(baseUrl, Ingredient.class, "1");
		
		System.out.println(ingredient.getBody());
		
		assertNotNull(ingredient);
	}
	
	@Test
	void test_ingredients_api() {
		/*final String baseUrl = "http://localhost:8080/api/ingredient";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ingredient[]> response = restTemplate.getForEntity(baseUrl, Ingredient[].class);
		
		List<Ingredient> ingredientsList = Arrays.asList(response.getBody());
		ingredientsList.forEach( System.out::println);
		assertNotNull(ingredientsList);*/
		
		
		final String baseUrl = "http://localhost:8080/api/ingredient";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CollectionModelList> response = restTemplate.getForEntity(baseUrl, CollectionModelList.class);
		
		CollectionModelList ingredientsList = response.getBody();
		ingredientsList.getIngredients().forEach( e -> System.out.println(e));
		
		assertNotNull(ingredientsList);
	}

}
