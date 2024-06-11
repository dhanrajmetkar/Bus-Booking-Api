package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import com.busboking.Bus_Booking_system.entity.ValueTable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface KeyTablesrvice {
    KeyTable findByBusIdString(String busIdString);
    List<ValueTable> findByBusIdStringValueTable(String busIdString);
}
