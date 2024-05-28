package com.busboking.Bus_Booking_system.repository;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyTableRepository extends JpaRepository<KeyTable,Integer> {
}
