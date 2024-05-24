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
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String busId;
    Integer fare;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="s", column=@Column(name="source")),
            @AttributeOverride(name="d", column=@Column(name="destination"))
    })
    BusRoute busRoute;
    String dates;


    public List<Date> getDates() {
        if (this.dates==null)
            return null;
        return Arrays.stream(dates.split(","))
                .map(dateStr -> {
                    try {
                        return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void setDates(List<Date> dates) {
        if(dates.size()==0)
            return;
        this.dates = dates.stream()
                .map(date -> new  SimpleDateFormat("dd/MM/yyyy").format(date))
                .collect(Collectors.joining(","));
    }

}