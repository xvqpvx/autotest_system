package com.caterpillar.exam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "criteria")
public class Criteria {

    @Id
    @Column(name = "id_criteria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_criteria;

    @Column(name = "criteria")
    private String criteria;

    @ManyToMany(mappedBy = "criterias")
    private List<InputParam> inputParameters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "criterias_values",
            joinColumns = @JoinColumn(name = "id_criteria"),
            inverseJoinColumns = @JoinColumn(name = "id_value")
    )
    private List<Values> values;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Criteria other = (Criteria) obj;
        // Сравнение только строковых значений поля criteria
        return Objects.equals(this.criteria, other.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteria);
    }


    @Override
    public String toString() {
        return criteria + '\'' +
                values;
    }
}
