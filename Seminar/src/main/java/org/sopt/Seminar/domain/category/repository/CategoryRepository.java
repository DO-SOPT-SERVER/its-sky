package org.sopt.Seminar.domain.category.repository;

import org.sopt.Seminar.domain.category.model.Category;
import org.sopt.Seminar.domain.category.model.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
}
