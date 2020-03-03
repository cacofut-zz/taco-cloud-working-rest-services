package br.com.salao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TacoCloud01Application implements WebMvcConfigurer/*, CommandLineRunner*/{
	
//    @PostConstruct
//    void started() {
//      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//    }
		
	public static void main(String[] args) {
		SpringApplication.run(TacoCloud01Application.class, args);
	}
	
	
	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		registry.addViewController("/").setViewName("home");
	}


}
