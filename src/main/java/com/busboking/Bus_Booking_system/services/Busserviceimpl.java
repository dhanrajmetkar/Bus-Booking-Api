package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.Bus;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Busserviceimpl implements Busservice{

    @Autowired
    BusRepository busRepository;

    @Override
    public String checkAvailable(String date) {
      List<Bus> buses= busRepository.findAll();
        if(0 >= buses.size())
        {
            return "no bus available";
        }
        for (Bus bus :buses ) {
            if (bus.getDates() == null)
            {
                bus.setDates(date);
                busRepository.save(bus);
                String busid = bus.getBusId();
                return "Booking Done With bus id :" + busid;
            }
            else if (bus.getDates().size() > 0) {
                boolean flag = false;
                for (String busDate : bus.getDates()) {
                    if (busDate.equals(date)) {
                        flag = true;
                    }
                }
                if (!flag) {
                    bus.setDates(date);
                    busRepository.save(bus);
                    String busid = bus.getBusId();
                    return "Booking Done With bus id :" + busid;
                }
            }
    }


        return " Bus Not available check for another date ";
    }

    @Override
    public Bus save(Bus bus) {
       return busRepository.save(bus);
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}
