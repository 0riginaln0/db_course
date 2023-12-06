package com.example.db_course.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "good_count")
    private Integer goodCount;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    @JsonIgnoreProperties("sales")
    private Good good;

    public Sale(Integer goodCount, ZonedDateTime createDate, Good good) {
        this.goodCount = goodCount;
        this.createDate = createDate;
        this.good = good;
    }

}
