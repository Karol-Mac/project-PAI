package net.enjoy.springboot.registrationlogin;

import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.ToDo;
import net.enjoy.springboot.registrationlogin.repository.ToDoRepository;
import net.enjoy.springboot.registrationlogin.repository.UserRepository;
import net.enjoy.springboot.registrationlogin.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class RegistrationLoginApplication {

    @Bean
    public CommandLineRunner initDatabase(UserService userService, UserRepository userRepository,
                                          ToDoRepository toDoRepository) {
        return args -> {
            if (userRepository.count() == 0) {

                UserDto user1 = new UserDto(0L, "student",
                        "student@pollub.edu.pl", "1234Pa$word");
                UserDto user2 = new UserDto(0L, "prowadzocy",
                        "prowadzocy@pollub.pl", "1234Pa$word");
                userService.saveUser(user1);
                userService.saveUser(user2);

                ToDo todo1 = new ToDo(1L, "zaliczyc PAI",
                        LocalDate.of(2024, 1, 29), false,
                        userRepository.findByEmail("student@pollub.edu.pl").get());

                ToDo todo2 = new ToDo(0L, "straszyc przed sesja",
                        LocalDate.of(2024, 1, 29), false,
                        userRepository.findByEmail("prowadzocy@pollub.pl").get());

                toDoRepository.save(todo1);
                toDoRepository.save(todo2);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(RegistrationLoginApplication.class, args);
    }
}