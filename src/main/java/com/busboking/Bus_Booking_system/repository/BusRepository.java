package com.busboking.Bus_Booking_system.repository;

import com.busboking.Bus_Booking_system.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {


}
