package com.accenture.Railway_Ticketing_System.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepo) {
        return args ->{
            User arbaaz = new User(
                    "Arbaaz",
                    "Arbaaz",
                    "Shaikh",
                    "arbaazshaikhbro@gmail.com",
                    "1234",
                    "08086305359"
            );

            User pratik = new User(
                    "Pratik",
                    "Pratik",
                    "Kamble",
                    "pratikkamblebro@gmail.com",
                    "1234",
                    "08086305360"
            );

            User shrinithya = new User(
                    "Shrinithya",
                    "Shrinithya",
                    ".",
                    "shrinithya.@gmail.com",
                    "1234",
                    "08086305361"
            );

            User bhavana = new User(
                    "Bhavana",
                    "Bhavana",
                    "Devi",
                    "bhavanadevi@gmail.com",
                    "1234",
                    "08086305362"
            );

            User purvi = new User(
                    "Purvi",
                    "Purvi",
                    "Maheshvari",
                    "purvimaheshvari@gmail.com",
                    "1234",
                    "08086305363"
            );

            User anishka = new User(
                    "Anishaka",
                    "Anishaka",
                    "Anishaka",
                    "anishka@gmail.com",
                    "1234",
                    "08086305364"
            );

            userRepo.saveAll(List.of(arbaaz,pratik,pratik,shrinithya,bhavana,anishka));


        };
    }

}