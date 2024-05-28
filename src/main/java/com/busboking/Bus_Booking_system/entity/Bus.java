package com.busboking.Bus_Booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_key_id")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="values", column=@Column(name="values"))
    })
    private KeyTable keyTable;
    }

