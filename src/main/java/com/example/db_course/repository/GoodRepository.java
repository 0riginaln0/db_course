package com.example.db_course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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

}