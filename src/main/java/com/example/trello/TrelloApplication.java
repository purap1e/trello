package com.example.trello;

import com.example.trello.entities.TaskCategories;
import com.example.trello.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CategoryRepository categoryRepository) {
		return args -> {
				categoryRepository.save(new TaskCategories(null, "hair"));
				categoryRepository.save(new TaskCategories(null, "electronic"));
				categoryRepository.save(new TaskCategories(null, "house"));
				categoryRepository.save(new TaskCategories(null, "animal"));
				categoryRepository.save(new TaskCategories(null, "clothes"));
		};
	}

}
