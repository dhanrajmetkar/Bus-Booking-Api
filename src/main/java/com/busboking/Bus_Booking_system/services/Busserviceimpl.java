package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.*;
import com.busboking.Bus_Booking_system.repository.BusRepository;
import com.busboking.Bus_Booking_system.repository.KeyTableRepository;
import com.busboking.Bus_Booking_system.repository.ValueTableRepository;
import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        List<Bus> buses = getAllAvailableBuses(request);
        if (buses.isEmpty()) {
            DateRequest dateRequest = DateRequest.builder()
                    .date(request.getDate())
                    .bookingStatus("Ticked Not Booked No Buses Available for given date and source and destination")
                    .customerDetails(customerDetails)
                    .build();
            return dateRequest;
        }
        for (Bus bus : buses) {
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
                CustomerDetails customerDetails=CustomerDetails.builder()
                        .busId(bus.getBusId())
                        .customerName(request.getCustomerDetails().getCustomerName())
                        .contact(request.getCustomerDetails().getContact())
                        .totalFare(bus.getFare())
                        .build();
                DateRequest dateRequest= DateRequest.builder()
                        .date(request.getDate())
                        .bookingStatus("Ticked Booked")
                        .customerDetails(customerDetails)
                        .busRoute(bus.getBusRoute())
                        .build();
                return dateRequest;
            }
            else {
                KeyTable k1 = keyTablesrvice.findByBusIdString(bus.getBusId());
                ValueTable v1 = new ValueTable();
                v1.setDate(request.getDate());
                v1.setKeyTable(k1);
                k1.getValueTables().add(v1);
                k1.setBus(bus);
                keyTableRepository.save(k1);
                bus.setKeyTable(k1);
                busRepository.save(bus);
                CustomerDetails customerDetails=CustomerDetails.builder()
                        .busId(bus.getBusId())
                        .customerName(request.getCustomerDetails().getCustomerName())
                        .contact(request.getCustomerDetails().getContact())
                        .totalFare(bus.getFare())

                        .build();
                DateRequest dateRequest= DateRequest.builder()
                        .date(request.getDate())
                        .bookingStatus("Ticked Booked")
                        .customerDetails(customerDetails)
                        .busRoute(bus.getBusRoute())
                        .build();
                return dateRequest;
            }

        }
        return null;
    }

    @Override
    public Bus save(Bus bus) {
        return busRepository.save(bus);
    }
    @Override
    public List<Bus> getAllAvailableBuses(DateRequest request) {
        List<Bus> busList = findByRouteSourceAndRouteDestination(request.getBusRoute().getS(),request.getBusRoute().getD());
        List<ValueTable> valueTables=valueTableRepository.findByDateNotEqual(request.getDate());
        if (busList.isEmpty()) {
            return busList;
        }
        if(valueTables.isEmpty())
        {
            return busList;
        }
        for (ValueTable valueTable : valueTables) {
            Bus temp=valueTable.getKeyTable().getBus();
            if(busList.contains(temp))
            {
                busList.remove(temp);
            }

        }
        return busList;
    }

    @Override
    public List<Bus> findByRouteSourceAndRouteDestination(String souce, String destination) {
        return busRepository.findByBusRoute_SAndBusRoute_D(souce, destination);
    }
}
