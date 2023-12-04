package com.example.db_course.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_id")
    private Long goodId;

    @Column(nullable = false)
    private String name;

    @Column
    private double priority;

    // one to many -> sales, warehouse1, warehouse2
    // @OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
    // @Column(nullable = false)
    // private List<Sale> Sales;

    public Good(String name) {
        this.name = name;
    }

    public Good(String name, double priority) {
        this.name = name;
        this.priority = priority;
    }

}
