package com.accenture.Railway_Ticketing_System.ticket;

import com.accenture.Railway_Ticketing_System.fares.FaresRepository;
import com.accenture.Railway_Ticketing_System.user.User;
import com.accenture.Railway_Ticketing_System.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final FaresRepository faresRepository;

    public TicketService(UserRepository userRepository, TicketRepository ticketRepository, FaresRepository faresRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.faresRepository = faresRepository;
    }

    @Transactional
    public String bookTicket(Ticket ticket, Model model) {
        List<String> stationList= faresRepository.getStations();
        Collections.sort(stationList);
        stationList = stationList.stream()
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("stations", stationList);

        // Log the username for debugging purposes
        String username = ticket.getUsername();
        System.out.println("Verifying user: " + username);

        // Fetch the user by username
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        // Check if user exists
        if (userOptional.isPresent()) {
            if (!ticket.getStartStation().equals(ticket.getEndStation())){
                if (ticket.getDate().isEqual(LocalDate.now()) || ticket.getDate().isAfter(LocalDate.now())){
                    System.out.println(ticket.getStartStation());
                    Optional<Integer> fare = faresRepository.getFare(ticket.getStartStation(), ticket.getEndStation());
                    if (fare.isPresent()){
                        int intFare = fare.get();
                        // Create a new ticket with the given details
                        Ticket newTicket = new Ticket(
                                ticket.getUsername(),
                                ticket.getStartStation(),
                                ticket.getEndStation(),
                                "Pending",
                                intFare,
                                ticket.getDate(),
                                ticket.getPaymentType()
                        );

                        // Save the new ticket to the repository
                        ticketRepository.save(newTicket);

                        if (ticket.getPaymentType().equals("creditCard")){
                            return "redirect:/payment";
                        } else{
                            return "redirect:/payment";
                        }
                        // Redirect to payment page

                    } else{
                        // Log the error for debugging purposes
                        System.out.println("No train available between selected stations");

                        // Add error message to the model
                        model.addAttribute("error", "No train available between selected stations");

                        // Return to the ticket booking page
                        return "ticket";
                    }



                } else {
                    // Log the error for debugging purposes
                    System.out.println("Select a valid date ");

                    // Add error message to the model
                    model.addAttribute("error", "Select a valid date");

                    // Return to the ticket booking page
                    return "ticket";
                }
            }
                else {
                // Log the error for debugging purposes
                System.out.println("Same source and destination station ");

                // Add error message to the model
                model.addAttribute("error", "Same source and destination station");

                // Return to the ticket booking page
                return "ticket";
            }

        } else {
            // Log the error for debugging purposes
            System.out.println("User Not Found for username: " + username);

            // Add error message to the model
            model.addAttribute("error", "User Not Found.");

            // Return to the ticket booking page
            return "ticket";
        }
    }
}
