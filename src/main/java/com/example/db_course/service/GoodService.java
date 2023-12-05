package com.example.db_course.service;

import org.springframework.stereotype.Service;

import com.example.db_course.repository.GoodRepository;
import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.model.Good;

import java.util.List;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GoodService {
    private final GoodRepository goodRepository;

    public List<Good> getAllGoods() {
        return (List<Good>) goodRepository.findAll();
    }

    public List<GetGoodsForShippingResponse> getGoodsForShipping() {
        return goodRepository.getGoodsForShipping();
    }
}
