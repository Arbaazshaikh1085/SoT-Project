package com.accenture.Railway_Ticketing_System.ticket;

import com.accenture.Railway_Ticketing_System.fares.FaresRepository;
import com.accenture.Railway_Ticketing_System.user.User;
import com.accenture.Railway_Ticketing_System.user.UserService;
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

    @GetMapping("/ticket")
    public String bookTicket(Model model) {
        List<String> stationList= faresRepository.getStations();
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
    public String choosePayment(){
        return "payment";
    }


}
