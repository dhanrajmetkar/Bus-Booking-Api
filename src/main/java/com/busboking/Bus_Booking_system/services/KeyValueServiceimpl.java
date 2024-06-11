package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import com.busboking.Bus_Booking_system.entity.ValueTable;
import com.busboking.Bus_Booking_system.repository.KeyTableRepository;
import com.busboking.Bus_Booking_system.repository.ValueTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeyValueServiceimpl implements KeyTablesrvice{
    @Autowired
    private KeyTableRepository keyTableRepository;
    @Autowired
    private ValueTableRepository valueTableRepository;

    @Override
    public KeyTable findByBusIdString(String busIdString) {
        return keyTableRepository.findByBusIdString(busIdString);
    }

    @Override
    public List<ValueTable> findByBusIdStringValueTable(String busIdString) {
        return keyTableRepository.findByBusIdStringValueTable(busIdString);
    }

}
