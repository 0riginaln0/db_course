package com.example.db_course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.db_course.model.Warehouse2;
import com.example.db_course.repository.Warehouse2Repository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Warehouse2Service {
    private final Warehouse2Repository warehouse2Repository;

    public List<Warehouse2> getAllWarehouse2() {
        return (List<Warehouse2>) warehouse2Repository.findAll();
    }
}
