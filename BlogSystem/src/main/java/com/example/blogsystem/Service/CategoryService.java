package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

final private CategoryRepository categoryRepository;


    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category) {
        Category oldCategory = categoryRepository.giveMeById(id);
        if (oldCategory == null) {
            throw new ApiException("Category not found");
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }
    public void deleteCategory(Integer id) {
        Category oldCategory = categoryRepository.findUserById(id);
        if (oldCategory == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(oldCategory);
    }

    public Category getCategoryIdByName(String name) {
        Category oldcategory = categoryRepository.findCategoryByName(name);
        if (oldcategory == null) {
            throw new ApiException("Category not found");
        }
        return oldcategory;
    }

}
