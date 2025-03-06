package com.accenture.Railway_Ticketing_System.ticket;

import com.accenture.Railway_Ticketing_System.creditCard.CreditCard;
import com.accenture.Railway_Ticketing_System.fares.FaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "")
public class TicketController {

    private final TicketService ticketService;
    private final FaresRepository faresRepository;

    @Autowired
    public TicketController(TicketService ticketService, FaresRepository faresRepository){
        this.ticketService = ticketService;
        this.faresRepository = faresRepository;
    }

    @GetMapping("/")
    public String home() {
        return "homepage"; // Maps to homepage.html
    }

    @GetMapping("/ticket")
    public String bookTicket(Model model) {
        List<String> stationList = faresRepository.getStations();
        Collections.sort(stationList);
        stationList = stationList.stream()
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("stations", stationList);
        return "ticket";
    }

    @PostMapping("/ticket")
    public String bookTicket(@ModelAttribute Ticket ticket, Model model) {
        return ticketService.bookTicket(ticket, model);
    }

    @GetMapping("/payment")
    public String choosePayment() {
        return "payment";
    }

    @GetMapping("/payment-options")
    public String showPaymentOptionsPage() {
        return "payment-options"; // Maps to payment-options.html
    }

    @GetMapping("/credit-card")
    public String showCreditCardPage(Model model) {
        model.addAttribute("creditCard", new CreditCard());
        return "credit-card"; // Maps to credit-card.html
    }

    @PostMapping("/process-credit-card")
    public String processCreditCard(@ModelAttribute CreditCard creditCard, Model model) {
        // Process credit card payment here
        // Add necessary attributes to the model if needed
        model.addAttribute("message", "Credit Card payment processed successfully!");
        return "payment-success"; // Maps to payment-success.html (you can create this page to show a success message)
    }

    @GetMapping("/bank-account")
    public String showBankAccountPage() {
        return "bank-account"; // Maps to bank-account.html
    }

    // Other methods...
}