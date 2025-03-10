package com.accenture.Railway_Ticketing_System.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findUserByUsername(String username);

    @Query("SELECT u.email FROM User u WHERE u.email = ?1")
    List<String> findUserByEmail(String Email);

}
