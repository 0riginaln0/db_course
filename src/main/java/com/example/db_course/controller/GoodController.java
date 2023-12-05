package com.example.db_course.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.model.Good;
import com.example.db_course.service.GoodService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("good")
@AllArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/all")
    public ResponseEntity<List<Good>> getAllGoods() {
        return new ResponseEntity<>(goodService.getAllGoods(), HttpStatus.OK);
    }

    @GetMapping("/for-shipping")
    public ResponseEntity<List<GetGoodsForShippingResponse>> getGoodsForShipping() {
        return new ResponseEntity<>(goodService.getGoodsForShipping(), HttpStatus.OK);
    }

}
