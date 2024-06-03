package com.busboking.Bus_Booking_system.repository;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import com.busboking.Bus_Booking_system.entity.ValueTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyTableRepository extends JpaRepository<KeyTable,Integer> {
    @Query("SELECT k.valueTables FROM KeyTable k WHERE k.busIdString = :busIdString")
    List<ValueTable> findByBusIdStringValueTable(@Param("busIdString") String busIdString);

    KeyTable findByBusIdString(String busIdString);
}
