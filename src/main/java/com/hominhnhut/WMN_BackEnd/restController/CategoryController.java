package com.hominhnhut.WMN_BackEnd.restController;

import com.hominhnhut.WMN_BackEnd.domain.enity.Category;
import com.hominhnhut.WMN_BackEnd.service.Interface.CategoryService;
import lombok.AccessLevel;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/category")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Set<Category>> getAllCategory() {
        Set<Category> categories = categoryService.selectAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable("id") Integer categoryId
    ) {
        Category category = categoryService.selectCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(
            @RequestBody Category category
    ) {
        Category categorySave = categoryService.saveCategory(category);
        return ResponseEntity.ok(categorySave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(
            @PathVariable("id") Integer categoryId
    ) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(
            @PathVariable("id") Integer categoryId,
            @RequestBody Category category
    ) {
        Category categoryUpdate = categoryService.updateCategory(category,categoryId);
        return ResponseEntity.ok(categoryUpdate);
    }
}
