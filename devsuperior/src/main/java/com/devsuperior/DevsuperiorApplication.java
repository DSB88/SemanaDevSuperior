package com.devsuperior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DevsuperiorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevsuperiorApplication.class, args);
	}

	@GetMapping("/home")
	public String home(){
		return "Olá Mundo";
	}
}
