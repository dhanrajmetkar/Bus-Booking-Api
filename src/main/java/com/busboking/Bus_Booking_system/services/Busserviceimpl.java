package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.*;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import com.busboking.Bus_Booking_system.repository.KeyTableRepository;
import com.busboking.Bus_Booking_system.repository.ValueTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.List;

@Service
public class Busserviceimpl implements Busservice{

    @Autowired
    BusRepository busRepository;
    @Autowired
    CustomerDetails customerDetails;
    @Autowired
    DateRequest dateRequest;
    @Autowired
    KeyTablesrvice keyTablesrvice;
    @Autowired
    KeyTableRepository keyTableRepository;
    @Autowired
    ValueTableRepository valueTableRepository;

    @Override
    public DateRequest checkAvailable(DateRequest request) throws ParseException {
        List<Bus> buses = busRepository.findAll();
        boolean busRoute = false;
        if (0 >= buses.size()) {
            dateRequest.setDate(request.getDate());
            dateRequest.setBookingStatus("Ticked Not Booked No Buses Available ");
            return dateRequest;
        }
        for (Bus bus : buses) {
            BusRoute r1=bus.getBusRoute();
            BusRoute r2=request.getBusRoute();
            if (r1.getS().equals(r2.getS())&&r2.getD().equals(r1.getD())) {
                busRoute = true;
                if (bus.getKeyTable() == null) {
                    KeyTable keyTable = new KeyTable();
                    keyTable.setBusIdString(bus.getBusId());
                    ValueTable valueTable = new ValueTable();
                    valueTable.setDate(request.getDate());
                    valueTable.setKeyTable(keyTable);
                    keyTable.getValueTables().add(valueTable);
                    keyTable.setBus(bus);
                    keyTableRepository.save(keyTable);
                    bus.setKeyTable(keyTable);
                    busRepository.save(bus);
                    String busid = bus.getBusId();
                    customerDetails.setCustomerName(request.getCustomerDetails().getCustomerName());
                    customerDetails.setContact(request.getCustomerDetails().getContact());
                    customerDetails.setBusId(busid);
                    customerDetails.setBusRoute(bus.getBusRoute());
                    customerDetails.setTotalFare(bus.getFare());
                    dateRequest.setDate(request.getDate());
                    dateRequest.setCustomerDetails(customerDetails);
                    dateRequest.setBookingStatus("Ticked Booked");
                    return dateRequest;
                } else {
                    boolean flag = false;
                    KeyTable k1 = keyTableRepository.findByBusIdString(bus.getBusId());
                    List<ValueTable> valueTables = keyTableRepository.findByBusIdStringValueTable(bus.getBusId());
                    for (ValueTable valueTable : valueTables) {
                        if (valueTable.getDate().equals(request.getDate())) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        ValueTable v1 = new ValueTable();
                        v1.setDate(request.getDate());
                        v1.setKeyTable(k1);
                        k1.getValueTables().add(v1);
                        k1.setBus(bus);
                        keyTableRepository.save(k1);
                        bus.setKeyTable(k1);
                        busRepository.save(bus);
                        String busid = bus.getBusId();
                        customerDetails.setCustomerName(request.getCustomerDetails().getCustomerName());
                        customerDetails.setContact(request.getCustomerDetails().getContact());
                        customerDetails.setBusId(busid);
                        customerDetails.setBusRoute(bus.getBusRoute());
                        customerDetails.setTotalFare(bus.getFare());
                        dateRequest.setDate(request.getDate());
                        dateRequest.setCustomerDetails(customerDetails);
                        dateRequest.setBookingStatus("Ticked Booked");
                        return dateRequest;
                    }
                }
            } else {
                busRoute = false;
            }
        }
        if (busRoute==true) {
            dateRequest.setDate(request.getDate());
            dateRequest.setCustomerDetails(null);
            dateRequest.setBookingStatus("Ticket Not Booked Check For Another Date");
            return dateRequest;
        } else {
            dateRequest.setDate(request.getDate());
            dateRequest.setCustomerDetails(null);
            dateRequest.setBookingStatus("Ticket Not Booked Check Due to bus not available on selected route");
            return dateRequest;
        }
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
