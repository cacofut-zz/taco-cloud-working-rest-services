package br.com.salao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.salao.entity.Ingredient;
import br.com.salao.resource.CollectionModelList;




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
