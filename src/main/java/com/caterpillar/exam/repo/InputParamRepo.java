package com.caterpillar.exam.repo;

import com.caterpillar.exam.model.InputParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputParamRepo extends JpaRepository<InputParam, Integer> {
    @Query("SELECT ip FROM InputParam ip " +
            "INNER JOIN ip.criterias c " +
            "INNER JOIN c.values v " +
            "WHERE v.value IN :criteriaList " +
            "GROUP BY ip " +
            "HAVING COUNT(DISTINCT v) = :criteriaCount")
    List<InputParam> findByCriteriaList(@Param("criteriaList") List<String> criteriaList,
                                        @Param("criteriaCount") int criteriaCount);

    InputParam findInputParamByParameter(String parameter);
}