package com.accenture.Railway_Ticketing_System.ticket;

import com.accenture.Railway_Ticketing_System.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

    }
