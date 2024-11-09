package com.project.ims.repository;

import com.project.ims.model.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportRepository extends JpaRepository<Export, Integer> {
}
