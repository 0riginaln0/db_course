package com.example.db_course.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.db_course.model.Good;
import com.example.db_course.model.Warehouse2;

public interface Warehouse2Repository extends CrudRepository<Warehouse2, Integer> {

    Warehouse2 getWarehouse2ByGood(Good good);
}
