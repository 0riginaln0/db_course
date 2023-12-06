package com.example.db_course.service;

import org.springframework.stereotype.Service;

import com.example.db_course.repository.GoodRepository;
import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.model.Good;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    public List<GetGoodsForShippingResponse> getGoodsForShipping(Integer maxCount) {
        return goodRepository.getGoodsForShipping(maxCount);
    }

    // public List<Integer> getMostUnsellableGoodIdForTimePeriod(
    // LocalDateTime tBegin, LocalDateTime tEnd) {
    // System.out.println(tBegin.toString());
    // System.out.println(tEnd.toString());
    // return (List<Integer>) goodRepository.getMostUnsellableGoodIdForTimePeriod(
    // tBegin.toString(), tEnd.toString());
    // }
}
