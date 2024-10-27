package com.ezbid.repository;
import com.ezbid.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface extends JpaRepository to perform CRUD operations on the Category entity
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
