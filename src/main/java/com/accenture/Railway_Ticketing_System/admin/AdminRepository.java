package com.accenture.Railway_Ticketing_System.admin;

import com.accenture.Railway_Ticketing_System.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long> {

    @Query("SELECT u FROM Admin u WHERE u.username = ?1")
    Optional<Admin> findUserByUsername(String username);

}
