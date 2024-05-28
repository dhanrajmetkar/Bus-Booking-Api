package com.busboking.Bus_Booking_system.entity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusRoute {
    private String s;
    private String d;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusRoute other = (BusRoute) o;
        return this.s == other.s && this.d==other.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }

}
