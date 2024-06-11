package com.busboking.Bus_Booking_system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails {
    private String busId;
    private String customerName;
    private String contact;
    private double totalFare;
}
