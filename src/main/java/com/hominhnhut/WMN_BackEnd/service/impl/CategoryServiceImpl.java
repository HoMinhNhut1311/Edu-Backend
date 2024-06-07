package com.hominhnhut.WMN_BackEnd.service.impl;

import com.hominhnhut.WMN_BackEnd.domain.enity.Category;
import com.hominhnhut.WMN_BackEnd.exception.errorType;
import com.hominhnhut.WMN_BackEnd.exception.myException.AppException;
import com.hominhnhut.WMN_BackEnd.repository.CategoryRepository;
import com.hominhnhut.WMN_BackEnd.service.Interface.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        Category categoryExist = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new AppException(errorType.notFound));
        categoryRepository.delete(categoryExist);
    }

    @Override
    public Category updateCategory(Category category, Integer categoryId) {
        Category categoryExist = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new AppException(errorType.notFound));
        category.setCategoryId(categoryId);
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> selectAllCategory() {
        return new HashSet<>(categoryRepository.findAll());
    }

    public Category selectCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new AppException(errorType.notFound)
        );
    }
}
