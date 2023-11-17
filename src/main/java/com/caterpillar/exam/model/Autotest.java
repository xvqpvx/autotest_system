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
@Table(name = "autotests")
public class Autotest {

    @Id
    @Column(name = "id_autotest")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autotest;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tests_params",
            joinColumns = @JoinColumn(name = "id_test"),
            inverseJoinColumns = @JoinColumn(name = "id_param")
    )
    private List<InputParam> parameters;
}
