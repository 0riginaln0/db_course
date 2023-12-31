package com.example.db_course.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.db_course.model.Good;
import com.example.db_course.model.Warehouse1;

public interface Warehouse1Repository extends CrudRepository<Warehouse1, Integer> {

    Warehouse1 getWarehouse1ByGood(Good good);
    
}
