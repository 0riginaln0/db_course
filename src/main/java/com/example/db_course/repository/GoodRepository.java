package com.example.db_course.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.db_course.custom_responses.GetDatesDemandComparisonResponse;
import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.model.Good;

public interface GoodRepository extends CrudRepository<Good, Integer> {
    @Query(value = """
            SELECT
            r.good_name AS goodName, r.good_id AS goodId,
            r.good_count AS goodCount,
            r.good_priority AS goodPriority
            FROM get_goods_for_shipping() AS r
            """, nativeQuery = true)
    List<GetGoodsForShippingResponse> getGoodsForShipping();

    @Query(value = """
            SELECT
            r.good_name AS goodName, r.good_id AS goodId,
            r.good_count AS goodCount,
            r.good_priority AS goodPriority
            FROM get_goods_for_shipping(?1) AS r
            """, nativeQuery = true)
    List<GetGoodsForShippingResponse> getGoodsForShipping(Integer maxCount);

    @Query(value = """
            SELECT
            *
            FROM good_id_minimum_sales_number_between(?1, ?2)
            """, nativeQuery = true)
    List<Integer> getMostUnsellableGoodIdForTimePeriod(ZonedDateTime tBegin, ZonedDateTime tEnd);

    @Query(value = """
            SELECT
            *
            FROM forecast_demand(?1, ?2, ?3)
            """, nativeQuery = true)
    List<Integer> getDemandForecastForTimePeriod(ZonedDateTime tBegin, ZonedDateTime tEnd,
            Integer goodId);

    @Query(value = """
            SELECT
            *
            FROM demand_increase_analysis(?1, ?2)
            """, nativeQuery = true)
    List<Integer> getMostDemandedGoodIdForTimePeriod(ZonedDateTime tBegin, ZonedDateTime tEnd);

    @Query(value = """
            SELECT
            r.sale_date AS saleDate
            FROM demand_comparison(?1, ?2) AS r
            """, nativeQuery = true)
    List<GetDatesDemandComparisonResponse> getDatesDemandComparison(Integer goodId1, Integer goodId2);

}
