package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.domain.Visits;
 
public interface VisitsRepository extends JpaRepository<Visits, Long> {
    Visits findByName(String name);
}
