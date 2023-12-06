package com.example.db_course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.db_course.model.Warehouse1;
import com.example.db_course.repository.Warehouse1Repository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Warehouse1Service {
    private final Warehouse1Repository warehouse1Repository;

    public List<Warehouse1> getAllWarehouse1() {
        return (List<Warehouse1>) warehouse1Repository.findAll();
    }
}
