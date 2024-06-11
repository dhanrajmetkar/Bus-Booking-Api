package com.busboking.Bus_Booking_system.entity;

import com.busboking.Bus_Booking_system.repository.BusRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;

import java.time.LocalDate;
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateRequest {
    private LocalDate date;
    CustomerDetails customerDetails;
    BusRoute busRoute;
    String bookingStatus;
}
