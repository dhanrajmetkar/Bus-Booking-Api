package com.busboking.Bus_Booking_system.repository;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import com.busboking.Bus_Booking_system.entity.ValueTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ValueTableRepository extends JpaRepository<ValueTable,Integer> {
    @Query("SELECT v FROM ValueTable v WHERE v.date = :localDate1")
    List<ValueTable> findByDateNotEqual(@Param("localDate1") LocalDate localDate1);

}
