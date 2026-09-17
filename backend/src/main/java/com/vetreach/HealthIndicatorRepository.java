package com.vetreach;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthIndicatorRepository extends JpaRepository<HealthIndicator, Long> {
}
