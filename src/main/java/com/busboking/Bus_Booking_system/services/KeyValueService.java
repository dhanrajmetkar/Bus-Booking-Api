package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface KeyValueService {
    void saveKeyValuePair(KeyTable keyTable);
}
