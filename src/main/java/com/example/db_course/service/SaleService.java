package com.example.db_course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.db_course.model.Sale;
import com.example.db_course.repository.SaleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return (List<Sale>) saleRepository.findAll();
    }
}
