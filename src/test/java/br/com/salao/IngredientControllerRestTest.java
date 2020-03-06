package br.com.salao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.salao.entity.Ingredient;
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
		final String baseUrl = "http://localhost:8080/api/ingredient";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ingredient[]> ingredients = restTemplate.getForEntity(baseUrl, Ingredient[].class);
		
		System.out.println(ingredients.getBody());
		for (Ingredient t : ingredients.getBody()) {
			System.out.println(t);
		}
		assertNotNull(ingredients.getBody());
	}

}
