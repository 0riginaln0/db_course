package com.example.db_course.service;

import org.springframework.stereotype.Service;

import com.example.db_course.repository.GoodRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.db_course.custom_responses.GetDatesDemandComparisonResponse;
import com.example.db_course.custom_responses.GetDemandChangeResponse;
import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.custom_responses.GetTop5PopularGoodsResponse;
import com.example.db_course.model.Good;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;
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

    public List<Integer> getMostUnsellableGoodIdForTimePeriod(ZonedDateTime tBegin,
            ZonedDateTime tEnd) {
        return (List<Integer>) goodRepository.getMostUnsellableGoodIdForTimePeriod(tBegin, tEnd);
    }

    public List<Integer> getDemandForecastForTimePeriod(ZonedDateTime tBegin, ZonedDateTime tEnd,
            Integer goodId) {
        return (List<Integer>) goodRepository.getDemandForecastForTimePeriod(tBegin, tEnd, goodId);
    }

    public List<GetDemandChangeResponse> getDemandChange(ZonedDateTime tBegin, ZonedDateTime tEnd,
            Integer goodId) {
        return (List<GetDemandChangeResponse>) goodRepository.getDemandChange(tBegin, tEnd, goodId);
    }

    public List<Integer> getMostDemandedGoodIdForTimePeriod(ZonedDateTime tBegin,
            ZonedDateTime tEnd) {
        return (List<Integer>) goodRepository.getMostDemandedGoodIdForTimePeriod(tBegin, tEnd);
    }

    public List<GetDatesDemandComparisonResponse> getDatesDemandComparison(Integer goodId1, 
        Integer goodId2) {
        return goodRepository.getDatesDemandComparison(goodId1, goodId2);
    }

    public List<GetTop5PopularGoodsResponse> getTop5PopularGoods(ZonedDateTime tBegin, ZonedDateTime tEnd) {
        return goodRepository.getTop5PopularGoods(tBegin, tEnd);
    }

    public List<Good> addNewGood(Good newGood) {
        Good addedGood = goodRepository.save(newGood);
        List<Good> returnValue = new ArrayList<>();
        returnValue.add(addedGood);
        return returnValue;
    }

    public List<Good> updateGood(Good updatedGood) {
        Good oldGood = goodRepository.findById(updatedGood.getGoodId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Good with id " + updatedGood.getGoodId() + " is not found"
                    ));
        if (updatedGood.getName() == null) {
            updatedGood.setName(oldGood.getName());
        }
        if (updatedGood.getPriority() == null) {
            updatedGood.setPriority(oldGood.getPriority());
        }
        Good returnGood = goodRepository.save(updatedGood);
        List<Good> returnValue = new ArrayList<>();
        returnValue.add(returnGood);
        return returnValue;
    }

    public List<Good> deleteGood(Good toDeleteGood) {
        Good returnGood = goodRepository.findById(toDeleteGood.getGoodId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Good with id " + toDeleteGood.getGoodId() + " is not found"
                    ));
        goodRepository.deleteById(toDeleteGood.getGoodId());
        List<Good> returnValue = new ArrayList<>();
        returnValue.add(returnGood);
        return returnValue;
    }

}
