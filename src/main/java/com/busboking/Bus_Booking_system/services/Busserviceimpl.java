package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.Bus;
import com.busboking.Bus_Booking_system.entity.CustomerDetails;
import com.busboking.Bus_Booking_system.entity.DateRequest;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Busserviceimpl implements Busservice{

    @Autowired
    BusRepository busRepository;
    @Autowired
    CustomerDetails customerDetails;

    @Autowired
    DateRequest dateRequest;

    @Override
    public DateRequest checkAvailable(DateRequest request) {
      List<Bus> buses= busRepository.findAll();
        if(0 >= buses.size())
        {
             dateRequest.setDate(request.getDate());
             dateRequest.setBookingStatus("Ticked Not Booked No Buses Available ");
             return dateRequest;
        }
        for (Bus bus :buses ) {
            if (bus.getDates() == null)
            {
                bus.setDates(request.getDate().toString());
                busRepository.save(bus);
                String busid = bus.getBusId();
                customerDetails.setCustomerName(request.getCustomerDetails().getCustomerName());
                customerDetails.setContact(request.getCustomerDetails().getContact());
                customerDetails.setBusId(busid);
                customerDetails.setRoute(bus.getBusRoute());
                customerDetails.setTotalFare(bus.getFare());
                dateRequest.setDate(request.getDate());
                dateRequest.setCustomerDetails(customerDetails);
                dateRequest.setBookingStatus("Ticked Booked");
                return dateRequest ;
            }
            else if (bus.getDates().size() > 0) {
                boolean flag = false;
                for (String busDate : bus.getDates()) {
                    if (busDate.equals(request.getDate().toString())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    bus.setDates(request.getDate().toString());
                    busRepository.save(bus);
                    String busid = bus.getBusId();
                    customerDetails.setCustomerName(request.getCustomerDetails().getCustomerName());
                    customerDetails.setContact(request.getCustomerDetails().getContact());
                    customerDetails.setBusId(busid);
                    customerDetails.setRoute(bus.getBusRoute());
                    customerDetails.setTotalFare(bus.getFare());
                    dateRequest.setDate(request.getDate());
                    dateRequest.setCustomerDetails(customerDetails);
                    dateRequest.setBookingStatus("Ticked Booked");
                    return dateRequest ;
                }
            }
    }

        dateRequest.setDate(request.getDate());
        dateRequest.setCustomerDetails(null);
        dateRequest.setBookingStatus("Ticket Not Booked Check For Another Date");
        return dateRequest;
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
