package com.project.ims.repository;

import com.project.ims.model.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportRepository extends JpaRepository<Import, Integer> {
}
