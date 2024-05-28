package com.busboking.Bus_Booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Embeddable
@Service
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String busId;

    @OneToMany(mappedBy = "keyTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ValueTable> values = new ArrayList<>();
}
