package org.sopt.Seminar.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.sopt.Seminar.domain.category.exception.CategoryNotFoundException;
import org.sopt.Seminar.domain.category.model.Category;
import org.sopt.Seminar.domain.category.model.CategoryId;
import org.sopt.Seminar.domain.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                CategoryNotFoundException::new);
    }
}
