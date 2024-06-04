package com.busboking.Bus_Booking_system.repository;

import com.busboking.Bus_Booking_system.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
    List<Bus> findByBusRoute_SAndBusRoute_D(String source, String destination);
}
