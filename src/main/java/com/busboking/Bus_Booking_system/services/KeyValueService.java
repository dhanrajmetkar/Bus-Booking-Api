package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface KeyValueService {
    public void saveKeyValuePair(String key, List<LocalDate> stringList);
    public void saveKeyValuePair(KeyTable keyTable);
}
