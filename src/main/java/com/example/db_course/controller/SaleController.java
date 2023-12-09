package com.example.db_course.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_course.model.Good;
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

    @DeleteMapping("/delete")
    public ResponseEntity<List<Sale>> deleteSale(@RequestBody Sale deletedSale) {
        var sale = saleService.deleteSale(deletedSale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping(path = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> addNewSale(@RequestBody Sale newSale) {
        var sale = saleService.addNewSale(newSale);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<List<Sale>> updateSale(@RequestBody Sale toUpdateSale) {
        var sale = saleService.updateSale(toUpdateSale);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

}
