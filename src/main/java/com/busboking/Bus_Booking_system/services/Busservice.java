package com.busboking.Bus_Booking_system.services;
import com.busboking.Bus_Booking_system.entity.Bus;
import java.text.ParseException;
import java.util.List;

public interface Busservice {

    String checkAvailable(String date) throws ParseException;

    Bus save(Bus bus);

    List<Bus> getAllBuses();
}
