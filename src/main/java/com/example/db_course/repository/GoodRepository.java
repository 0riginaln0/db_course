package com.example.db_course.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.db_course.custom_responses.GetDatesDemandComparisonResponse;
import com.example.db_course.custom_responses.GetDemandChangeResponse;
import com.example.db_course.custom_responses.GetGoodsForShippingResponse;
import com.example.db_course.custom_responses.GetTop5PopularGoodsResponse;
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


    @Query(value = """
        SELECT goods.name, sum(sales.good_count) AS popularity, goods.priority
        FROM goods
        JOIN sales ON sales.good_id = goods.good_id
        WHERE sales.create_date >= ?1 AND sales.create_date <= ?2
            GROUP BY goods.good_id
            ORDER BY (sum(sales.good_count)) DESC, goods.priority DESC
            LIMIT 5;
        """, nativeQuery = true)
    List<GetTop5PopularGoodsResponse> getTop5PopularGoods(ZonedDateTime tBegin, ZonedDateTime tEnd);

    @Query(value = """
        SELECT SUM(sales.good_count) as demand, DATE(sales.create_date), goods."name"
        FROM sales
        JOIN goods on sales.good_id = goods.good_id
        WHERE (sales.good_id = ?3) and sales.create_date BETWEEN ?1 AND ?2
            GROUP BY DATE(sales.create_date), goods."name"
            ORDER BY DATE(sales.create_date);
            """, nativeQuery = true)
    List<GetDemandChangeResponse> getDemandChange(ZonedDateTime tBegin, ZonedDateTime tEnd,
            Integer goodId);
    
}
