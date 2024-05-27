package com.busboking.Bus_Booking_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRequest {
    private LocalDate date;
    CustomerDetails customerDetails;
    String bookingStatus;
}
