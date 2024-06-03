package com.busboking.Bus_Booking_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Key;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "busId",
                columnNames = "busId"
        )
)
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bid;
    @Column(
            unique = true,
            nullable = false
    )
    String busId;
    Integer fare;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="s", column=@Column(name="source")),
            @AttributeOverride(name="d", column=@Column(name="destination"))
    })
    private BusRoute busRoute;
    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "bus"
    )
    @JsonBackReference
    private KeyTable keyTable;
    @Override
    public int hashCode() {
        return Objects.hash(bid, busId, fare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(bid, bus.bid) &&
                Objects.equals(busId, bus.busId) &&
                Objects.equals(fare, bus.fare);
    }
  }

