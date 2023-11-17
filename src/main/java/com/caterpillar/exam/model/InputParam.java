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
@Table(name = "input_parameters")
public class InputParam {

    @Id
    @Column(name = "id_input_parameters")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_parameter;

    @Column(name = "parameter")
    private String parameter;

    @ManyToMany(mappedBy = "parameters")
    private List<Autotest> autotests;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "param_criteria",
            joinColumns = @JoinColumn(name = "id_param"),
            inverseJoinColumns = @JoinColumn(name = "id_criteria")
    )
    private List<Criteria> criterias;

}
