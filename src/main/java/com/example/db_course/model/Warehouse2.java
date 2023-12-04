package com.example.db_course.model;

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
@Table(name = "warehouse2")
public class Warehouse2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse2_id")
    private Long warehouse2Id;

    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    @JsonIgnoreProperties("warehouse2")
    private Good good;

    @Column(name = "good_count")
    private Long goodCount;

    public Warehouse2(Good good, Long goodCount) {
        this.good = good;
        this.goodCount = goodCount;
    }

}
