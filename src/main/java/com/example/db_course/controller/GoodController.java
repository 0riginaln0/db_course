package com.example.db_course.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/for-shipping/{maxCount}")
    public ResponseEntity<List<GetGoodsForShippingResponse>> getGoodsForShipping(
            @PathVariable Integer maxCount) {
        return new ResponseEntity<>(goodService.getGoodsForShipping(maxCount), HttpStatus.OK);
    }

    @GetMapping("/most-unsellable/{tBegin}/{tEnd}")
    public ResponseEntity<List<Integer>> getMostUnsellableGoodIdForTimePeriod(
            @PathVariable String tBegin, @PathVariable String tEnd) {

        ZonedDateTime zonedDateTimeBegin = ZonedDateTime.parse(tBegin);
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.parse(tEnd);

        return new ResponseEntity<>(goodService.getMostUnsellableGoodIdForTimePeriod(
                zonedDateTimeBegin, zonedDateTimeEnd), HttpStatus.OK);
    }

    @GetMapping("/demand-forecast/{tBegin}/{tEnd}/{goodId}")
    public ResponseEntity<List<Integer>> getDemandForecastForTimePeriod(
            @PathVariable String tBegin, @PathVariable String tEnd, @PathVariable Integer goodId) {
        System.out.println(goodId);
        ZonedDateTime zonedDateTimeBegin = ZonedDateTime.parse(tBegin);
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.parse(tEnd);

        return new ResponseEntity<>(goodService.getDemandForecastForTimePeriod(
                zonedDateTimeBegin, zonedDateTimeEnd, goodId), HttpStatus.OK);
    }

    @GetMapping("/demand-increase/{tBegin}/{tEnd}")
    public ResponseEntity<List<Integer>> getMostDemandedGoodIdForTimePeriod(
            @PathVariable String tBegin, @PathVariable String tEnd) {

        ZonedDateTime zonedDateTimeBegin = ZonedDateTime.parse(tBegin);
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.parse(tEnd);

        return new ResponseEntity<>(goodService.getMostDemandedGoodIdForTimePeriod(
                zonedDateTimeBegin, zonedDateTimeEnd), HttpStatus.OK);
    }
}
