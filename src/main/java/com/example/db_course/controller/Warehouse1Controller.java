package com.example.db_course.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_course.model.Warehouse1;
import com.example.db_course.service.Warehouse1Service;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("warehouse1")
@AllArgsConstructor
public class Warehouse1Controller {

    private final Warehouse1Service warehouse1Service;

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse1>> getAllWarehouse1() {
        return new ResponseEntity<>(warehouse1Service.getAllWarehouse1(), HttpStatus.OK);
    }

}
