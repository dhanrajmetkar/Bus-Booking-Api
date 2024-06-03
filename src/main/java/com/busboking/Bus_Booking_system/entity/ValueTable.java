package com.busboking.Bus_Booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Entity
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValueTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "keyId", referencedColumnName = "id")
    private KeyTable keyTable;
}
