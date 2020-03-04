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
	void test_ingredients_api() {
		final String baseUrl = "http://localhost:8080/api/ingredient/{id}";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ingredient> ingredient = 
				restTemplate.getForEntity(baseUrl, Ingredient.class, "1");
		
		System.out.println(ingredient.getBody());
		
		assertNotNull(ingredient);
	}

}
