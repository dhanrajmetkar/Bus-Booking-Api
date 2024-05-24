package com.busboking.Bus_Booking_system.controller;
import com.busboking.Bus_Booking_system.entity.Bus;
import com.busboking.Bus_Booking_system.services.Busservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class Buscontroller {
    @Autowired
    Busservice busservice;
    @PostMapping("/saveBus")
    public Bus saveBus(@RequestBody Bus bus)
    {
        return busservice.save(bus);
    }

    @PostMapping("/available")
    public String checkAvailability(@RequestBody String date) throws ParseException {

        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date date1=formatter1.parse(date);
        return busservice.checkAvailable(date1);
    }
}
