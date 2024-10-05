package com.example.demo.repository;

import com.example.demo.entity.HazardData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HazardDataRepository extends JpaRepository<HazardData, Long> {
}
