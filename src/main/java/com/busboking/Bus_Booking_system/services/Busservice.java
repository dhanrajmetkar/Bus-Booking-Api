package com.busboking.Bus_Booking_system.services;
import com.busboking.Bus_Booking_system.entity.Bus;
import com.busboking.Bus_Booking_system.entity.DateRequest;
import java.text.ParseException;
import java.util.List;

public interface Busservice {

    DateRequest checkAvailable(DateRequest date) throws ParseException;

    Bus save(Bus bus);

    List<Bus> getAllBuses();
    List<Bus> findByRouteSourceAndRouteDestination(String souce, String destination);
}
