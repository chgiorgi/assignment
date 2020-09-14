package com.helmes.assignment.server.repository;

import com.helmes.assignment.server.model.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorsRepository extends JpaRepository<SectorEntity, Long> {
}
