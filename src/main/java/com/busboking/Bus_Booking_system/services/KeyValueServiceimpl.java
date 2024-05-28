package com.busboking.Bus_Booking_system.services;

import com.busboking.Bus_Booking_system.entity.KeyTable;
import com.busboking.Bus_Booking_system.entity.ValueTable;
import com.busboking.Bus_Booking_system.repository.KeyTableRepository;
import com.busboking.Bus_Booking_system.repository.ValueTableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class KeyValueServiceimpl implements KeyValueService{

    @Autowired
    KeyTableRepository keyTableRepository;
    @Autowired
    ValueTableRepository valueTableRepository;
    @Override
    public void saveKeyValuePair(String key, List<LocalDate> values) {
        KeyTable keyTable=new KeyTable();
        keyTable.setBusId(key);
        for (LocalDate value : values) {
            ValueTable valueTable = new ValueTable();
            valueTable.setDate(value);
            valueTable.setKeyTable(keyTable);
            keyTable.getValues().add(valueTable);
        }
        keyTableRepository.save(keyTable);
    }

    @Override
    public void saveKeyValuePair(KeyTable keyTable) {
        KeyTable keyTable1=new KeyTable();
        keyTable.setBusId(keyTable.getBusId());
        for (ValueTable value : keyTable.getValues()) {
            ValueTable valueTable = new ValueTable();
            valueTable.setDate(value.getDate());
            valueTable.setKeyTable(keyTable1);
            keyTable.getValues().add(valueTable);
        }
        keyTableRepository.save(keyTable);
    }
}
