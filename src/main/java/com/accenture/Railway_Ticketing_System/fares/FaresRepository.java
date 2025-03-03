package com.accenture.Railway_Ticketing_System.fares;

import com.accenture.Railway_Ticketing_System.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaresRepository extends JpaRepository<Fares, Long> {

    @Query("SELECT f.fare FROM Fares f WHERE f.source = :startStation AND f.destination = :endStation")
    Optional<Integer> getFare(@Param("startStation") String startStation, @Param("endStation") String endStation);

    @Query("SELECT f.source FROM Fares f")
    List<String> getStations();
}
