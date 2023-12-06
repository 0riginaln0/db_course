package com.example.db_course.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_course.model.Sale;
import com.example.db_course.service.SaleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("sale")
@AllArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/all")
    public ResponseEntity<List<Sale>> getAllSales() {
        return new ResponseEntity<>(saleService.getAllSales(), HttpStatus.OK);
    }

}
