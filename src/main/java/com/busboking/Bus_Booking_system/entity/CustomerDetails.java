package com.busboking.Bus_Booking_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDetails {
    private String busId;
    private String customerName;
    private String contact;
    private BusRoute route;
    private double totalFare;
}
