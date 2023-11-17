package com.caterpillar.exam.repo;

import com.caterpillar.exam.model.Values;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuesRepo extends JpaRepository<Values, Integer> {
    Values findValuesByValue(String value);
}
