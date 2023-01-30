package com.main.JSONDATA;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.JSONDATA.domain.User;
import com.main.JSONDATA.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class JsondataApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsondataApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService){
		return args ->{
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
			// initialize list of users reference from model
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			//read json file from location
			try {
				List<User> users = mapper.readValue(inputStream,typeReference);
				// get the list of users map and read the value from input stream and type reference
				userService.save(users);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};

	}

}
