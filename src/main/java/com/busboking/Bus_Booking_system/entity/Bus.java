package com.busboking.Bus_Booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    Integer id;
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
    BusRoute busRoute;

    @ManyToOne
    @JoinColumn(name = "key_table_id")
    private KeyTable keyTable;

    public boolean isEmptyKeytable() {
        return keyTable == null || keyTable.isEmptyKeyTable();
    }


    }

