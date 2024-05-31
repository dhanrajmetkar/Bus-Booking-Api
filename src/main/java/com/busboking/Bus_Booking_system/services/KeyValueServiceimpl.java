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
public class KeyValueServiceimpl implements KeyValueService{

    @Autowired
    KeyTableRepository keyTableRepository;
    @Override
    public void saveKeyValuePair(KeyTable keyTable) {
        KeyTable keyTable1=new KeyTable();
        keyTable1.setBusId(keyTable.getBusId());
        for (ValueTable value : keyTable.getValueTables()) {
          value.setKeyTable(keyTable1);
          keyTable1.getValueTables().add(value);
        }
        keyTableRepository.save(keyTable1);
    }

    @Autowired
    private ValueTableRepository valueTableRepository;

    @Transactional
    public void addValueTableToKeyTable(Integer keyTableId, ValueTable valueTable) {
        KeyTable keyTable = keyTableRepository.findById(keyTableId).orElseThrow(() -> new RuntimeException("KeyTable not found"));
        valueTable.setKeyTable(keyTable);
        valueTableRepository.save(valueTable);
    }

    public List<ValueTable> getValueTablesByKeyTable(Integer keyTableId) {
        KeyTable keyTable = keyTableRepository.findById(keyTableId).orElseThrow(() -> new RuntimeException("KeyTable not found"));
        return keyTable.getValueTables();
    }
}
