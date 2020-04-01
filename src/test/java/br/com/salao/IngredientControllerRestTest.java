package br.com.salao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.salao.entity.Ingredient;



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
		
		
		for (Ingredient t : ingredients.getBody()) {
			System.out.println(t);
		}
		assertNotNull(ingredients.getBody());
	}
	
	@Test
	void test_put_ingredient_test() {
		final String url = "http://localhost:8080/api/ingredient";
		RestTemplate restTemplate = new RestTemplate();
		Ingredient ingred = new Ingredient(10L, "SRCR", "Sour cream Up", Ingredient.Type.SAUCE);
		restTemplate.put(url, ingred, ingred.getId());
		
	}
	
	@Test
	void test_post_ingredient_test() {
		final String url = "http://localhost:8080/api/ingredient";
		RestTemplate restTemplate = new RestTemplate();
		Ingredient ingred = new Ingredient(null, "Novo", "Novo Ingredient", Ingredient.Type.VEGGIES);
		restTemplate.postForEntity(url, ingred, Ingredient.class);
		
	}
	
	@Test
	void test_delete_ingredient_test() {
		final String url = "http://localhost:8080/api/ingredient/{id}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, 11);
		
	}
	
	@Test
	void test_patch_ingredient_test() {
		final String url = "http://localhost:8080/api/ingredient/{id}";
		RestTemplate restTemplate = new RestTemplate();
		Ingredient ingred = new Ingredient(10L, "Novo PUT", "Novo Ingredient PUT", Ingredient.Type.WRAP);
		restTemplate.patchForObject(url, ingred, Ingredient.class, "10");
		
	}

}
