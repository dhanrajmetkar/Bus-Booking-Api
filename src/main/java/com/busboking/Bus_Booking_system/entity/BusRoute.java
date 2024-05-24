package com.busboking.Bus_Booking_system.entity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusRoute {
    private String s;
    private String d;
}
