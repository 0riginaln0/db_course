package com.example.db_course.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_course.model.Warehouse2;
import com.example.db_course.service.Warehouse2Service;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("warehouse2")
@AllArgsConstructor
public class Warehouse2Controller {

    private final Warehouse2Service warehouse2Service;

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse2>> getAllWarehouse1() {
        return new ResponseEntity<>(warehouse2Service.getAllWarehouse2(), HttpStatus.OK);
    }

}
