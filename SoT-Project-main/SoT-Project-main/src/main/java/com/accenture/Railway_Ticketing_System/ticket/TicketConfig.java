package com.accenture.Railway_Ticketing_System.ticket;

import com.accenture.Railway_Ticketing_System.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TicketConfig {


    @Bean
    CommandLineRunner ticketCommandLineRunner(TicketRepository TicketRepo) {
        return args -> {
            TicketRepo.saveAll(List.of());
        };
    }
}