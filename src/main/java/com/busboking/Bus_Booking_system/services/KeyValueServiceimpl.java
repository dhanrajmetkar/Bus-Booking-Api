package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import com.busboking.Bus_Booking_system.entity.ValueTable;
import com.busboking.Bus_Booking_system.repository.KeyTableRepository;
import com.busboking.Bus_Booking_system.repository.ValueTableRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyValueServiceimpl implements KeyTablesrvice{
    @Autowired
    private KeyTableRepository keyTableRepository;

    @Override
    public KeyTable getKeyTableByBusIdString(String busIdString) {
        return keyTableRepository.findByBusIdString(busIdString);
    }


}
