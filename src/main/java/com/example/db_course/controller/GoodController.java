package com.example.db_course.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db_course.custom_responses.GetDatesDemandComparisonResponse;
import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.model.Good;
import com.example.db_course.service.GoodService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("good")
@AllArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/all")
    public ResponseEntity<List<Good>> getAllGoods() {
        return new ResponseEntity<>(goodService.getAllGoods(), HttpStatus.OK);
    }

    @PostMapping(path = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Good>> addNewGood(@RequestBody Good newGood) {
        var good = goodService.addNewGood(newGood);
        return new ResponseEntity<>(good, HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<List<Good>> updateGood(@RequestBody Good updatedGood) {
        var good = goodService.updateGood(updatedGood);
        return new ResponseEntity<>(good, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Good>> deleteGood(@RequestBody Good deletedGood) {
        var good = goodService.deleteGood(deletedGood);
        return new ResponseEntity<>(good, HttpStatus.OK);
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

    @GetMapping("/demand-comparison/{goodId1}/{goodId2}")
    public ResponseEntity<List<GetDatesDemandComparisonResponse>> getDatesDemandComparison(
        @PathVariable Integer goodId1, @PathVariable Integer goodId2) {
        return new ResponseEntity<>(goodService.getDatesDemandComparison(goodId1, goodId2),
                HttpStatus.OK);
    }
}
