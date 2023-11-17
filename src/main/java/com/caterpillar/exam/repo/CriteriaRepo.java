package com.caterpillar.exam.repo;

import com.caterpillar.exam.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepo extends JpaRepository<Criteria, Integer> {
    Criteria findByCriteria(String criteria);
}
