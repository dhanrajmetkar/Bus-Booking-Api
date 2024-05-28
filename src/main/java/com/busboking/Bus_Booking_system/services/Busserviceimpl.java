package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.*;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import org.hibernate.sql.ast.tree.insert.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
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

//    @Override
//    public DateRequest checkAvailable(DateRequest request) {
//      List<Bus> buses= busRepository.findAll();
//        if(0 >= buses.size())
//        {
//             dateRequest.setDate(request.getDate());
//             dateRequest.setBookingStatus("Ticked Not Booked No Buses Available ");
//             return dateRequest;
//        }
//        for (Bus bus :buses ) {
//            if (bus.getDates() == null)
//            {
//                bus.setDates(request.getDate().toString());
//                busRepository.save(bus);
//                String busid = bus.getBusId();
//                customerDetails.setCustomerName(request.getCustomerDetails().getCustomerName());
//                customerDetails.setContact(request.getCustomerDetails().getContact());
//                customerDetails.setBusId(busid);
//                customerDetails.setRoute(bus.getBusRoute());
//                customerDetails.setTotalFare(bus.getFare());
//                dateRequest.setDate(request.getDate());
//                dateRequest.setCustomerDetails(customerDetails);
//                dateRequest.setBookingStatus("Ticked Booked");
//                return dateRequest ;
//            }
//            else if (!bus.getDates().isEmpty()) {
//                boolean flag = false;
//                for (String busDate : bus.getDates()) {
//                    if (busDate.equals(request.getDate().toString())) {
//                        flag = true;
//                        break;
//                    }
//                }
//                if (!flag) {
//                    bus.setDates(request.getDate().toString());
//                    busRepository.save(bus);
//                    String busid = bus.getBusId();
//                    customerDetails.setCustomerName(request.getCustomerDetails().getCustomerName());
//                    customerDetails.setContact(request.getCustomerDetails().getContact());
//                    customerDetails.setBusId(busid);
//                    customerDetails.setRoute(bus.getBusRoute());
//                    customerDetails.setTotalFare(bus.getFare());
//                    dateRequest.setDate(request.getDate());
//                    dateRequest.setCustomerDetails(customerDetails);
//                    dateRequest.setBookingStatus("Ticked Booked");
//                    return dateRequest ;
//                }
//            }
//    }
//
//        dateRequest.setDate(request.getDate());
//        dateRequest.setCustomerDetails(null);
//        dateRequest.setBookingStatus("Ticket Not Booked Check For Another Date");
//        return dateRequest;
//    }

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
            if (bus.getKeyTable() == null) {

                KeyTable keyTable = new KeyTable();
                keyTable.setBusId(bus.getBusId());
                ValueTable valueTable = new ValueTable();
                valueTable.setDate(request.getDate());
                List<ValueTable> values = keyTable.getValues();
                values.add(valueTable);
                keyTable.setValues(values);
                keyValueService.saveKeyValuePair(keyTable);
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
            else if(bus.getKeyTable()!=null){
                return null;
            }
        }
//        dateRequest.setDate(request.getDate());
//        dateRequest.setCustomerDetails(null);
//        dateRequest.setBookingStatus("Ticket Not Booked Check For Another Date");
//        return dateRequest;
        return null;

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
