package com.busboking.Bus_Booking_system.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @OneToOne(
            cascade =CascadeType.ALL
    )
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    @JsonManagedReference
    private Bus bus;
    private String busIdString;
    @OneToMany(
            mappedBy = "keyTable",
            cascade = CascadeType.ALL,
            fetch =FetchType.EAGER
    )
    private  List<ValueTable> valueTables=new ArrayList<>();
    @Override
    public int hashCode() {
        return Objects.hash(id, busIdString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyTable keyTable = (KeyTable) o;
        return Objects.equals(id, keyTable.id) &&
                Objects.equals(busIdString, keyTable.busIdString);
    }

}
