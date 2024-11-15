package com.project.ims.repository;

import com.project.ims.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductNameContaining(String name);

//    @EntityGraph(attributePaths = {"suppliers"})
    @Query("""
    SELECT p, s.supplierID, s.name\s
    FROM Product p
    LEFT JOIN p.suppliers s
   \s""")
    Page<Object[]> getAll(Pageable pageable);

    @Query("SELECT p.productID FROM Product p WHERE p.productID = :productId")
    Optional<Integer> findProductIdByProductId(@Param("productId") int productId);
}
