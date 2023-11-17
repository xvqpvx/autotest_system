package com.caterpillar.exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "values_s")
public class Values {

    @Id
    @Column(name = "id_value")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_value;

    @Column(name = "value_str")
    private String value;

    @ManyToMany(mappedBy = "values")
    private List<Criteria> criterias;
}
