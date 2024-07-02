package org.example.bookmyshow;

import org.example.bookmyshow.Controller.UserController;
import org.example.bookmyshow.Dtos.SignUpRequestDto;
import org.example.bookmyshow.Dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import   org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;
	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class,args);
	}
	@Override
	public void run(String... args) throws Exception {

		SignUpRequestDto requestDto = new SignUpRequestDto();
		requestDto.setName("Aditya");
		requestDto.setEmail("aditya@123");
		requestDto.setPassword("122344");

		SignUpResponseDto responseDto = userController.signUp(requestDto);
	}
}


