package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.Bus;

import java.text.ParseException;
import java.util.Date;

public interface Busservice {
    String getBus();

    String checkAvailable(Date date) throws ParseException;

    Bus save(Bus bus);
}
