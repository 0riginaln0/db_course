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

    public List<Integer> getMostUnsellableGoodIdForTimePeriod(
            ZonedDateTime tBegin, ZonedDateTime tEnd) {
        return (List<Integer>) goodRepository.getMostUnsellableGoodIdForTimePeriod(
                tBegin, tEnd);
    }

    public List<Integer> getDemandForecastForTimePeriod(
            ZonedDateTime tBegin, ZonedDateTime tEnd, Integer goodId) {
        return (List<Integer>) goodRepository.getDemandForecastForTimePeriod(
                tBegin, tEnd, goodId);
    }

    public List<Integer> getMostDemandedGoodIdForTimePeriod(
            ZonedDateTime tBegin, ZonedDateTime tEnd) {
        return (List<Integer>) goodRepository.getMostDemandedGoodIdForTimePeriod(
                tBegin, tEnd);
    }
}
