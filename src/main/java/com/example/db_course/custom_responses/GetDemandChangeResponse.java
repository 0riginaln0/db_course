package com.example.db_course.custom_responses;

import java.time.LocalDate;

public interface GetDemandChangeResponse {
    Long getDemand();

    LocalDate getDate();

    String getName();
}
