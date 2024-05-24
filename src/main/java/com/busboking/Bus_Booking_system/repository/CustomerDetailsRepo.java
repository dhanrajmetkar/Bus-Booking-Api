package com.busboking.Bus_Booking_system.repository;

import com.busboking.Bus_Booking_system.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails,Integer> {
}
