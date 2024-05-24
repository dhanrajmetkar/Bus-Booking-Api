package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.Bus;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Busserviceimpl implements Busservice{
    @Autowired
    BusRepository busRepository;

    @Override
    public String getBus() {
        return "bus2";
    }
    public String checkAvailable(Date date) throws ParseException {
        for (Bus bus : busRepository.findAll()) {
            if(bus.getDates()==null)
            {
                List<Date> lt =new ArrayList<>();
                lt.add(date);
                bus.setDates(lt);
            }
            for (Date busDate : bus.getDates()) {
                if(busDate.equals(date)) {
                    return "not available";
                }
                List<Date> lt =new ArrayList<>();
                lt.add(date);
                bus.setDates(lt);
                return "date added";
            }

        }

        return "available";
    }

    @Override
    public Bus save(Bus bus) {
       return busRepository.save(bus);
    }
}
