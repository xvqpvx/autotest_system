package com.caterpillar.exam.repo;

import com.caterpillar.exam.model.Autotest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutotestRepo extends JpaRepository<Autotest, Integer> {
    Autotest findAutotestsByName(String name);
}
