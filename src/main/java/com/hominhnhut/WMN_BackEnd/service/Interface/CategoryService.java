package com.hominhnhut.WMN_BackEnd.service.Interface;

import com.hominhnhut.WMN_BackEnd.domain.enity.Category;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category);

    void deleteCategoryById(Integer categoryId);

    Category updateCategory(Category category, Integer categoryId);

    Set<Category> selectAllCategory();

    Category selectCategoryById(Integer id);

}
