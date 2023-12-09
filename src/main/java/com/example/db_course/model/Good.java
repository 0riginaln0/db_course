package com.example.db_course.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Integer goodId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float priority;

    // one to many -> sales, warehouse1, warehouse2
    // @OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
    // @Column(nullable = false)
    // private List<Sale> Sales;

    public Good(String name) {
        this.name = name;
        this.priority = (float) 0.0;
    }

    public Good(String name, Float priority) {
        this.name = name;
        this.priority = priority;
    }

}
