package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.*;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import com.busboking.Bus_Booking_system.repository.KeyTableRepository;
import com.busboking.Bus_Booking_system.repository.ValueTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.ArrayList;
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
    KeyValueService keyValueService;
    @Autowired
    KeyTableRepository keyTableRepository;
    @Autowired
    ValueTableRepository valueTableRepository;

    @Override
    public DateRequest checkAvailable(DateRequest request) throws ParseException {
        List<Bus> buses=busRepository.findAll();
        if(0 >= buses.size())
        {
             dateRequest.setDate(request.getDate());
             dateRequest.setBookingStatus("Ticked Not Booked No Buses Available ");
             return dateRequest;
        }
        for (Bus bus : buses) {
            if (bus.isEmptyKeytable()) {
                KeyTable keyTable = new KeyTable();
                keyTable.setBusId(bus.getBusId());
                ValueTable valueTable = new ValueTable();
                valueTable.setDate(request.getDate());

                List<ValueTable> values = keyTable.getValueTables();
                if (values == null) {
                    values = new ArrayList<>();
                    keyTable.setValueTables(values);
                }else {

                values.add(valueTable);
                }
                keyTable.setValueTables(values);
                //keyValueService.saveKeyValuePair(keyTable);
                keyTable = keyTableRepository.save(keyTable);
                bus.setKeyTable(keyTable);
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
                return dateRequest;
            }

        }
        dateRequest.setDate(request.getDate());
        dateRequest.setCustomerDetails(null);
        dateRequest.setBookingStatus("Ticket Not Booked Check For Another Date");
        return dateRequest;


    }

    @Override
    public Bus save(Bus bus) {
        KeyTable keyTable = bus.getKeyTable();
        if (keyTable != null && keyTable.getId() == null) {
            keyTableRepository.save(keyTable);
            for (ValueTable valueTable : keyTable.getValueTables()) {
                valueTable.setKeyTable(keyTable);
                valueTableRepository.save(valueTable);
            }
        }
        return busRepository.save(bus);
    }
    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}
