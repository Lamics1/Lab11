package com.example.blogsystem.Controller;
import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
final private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategory());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added successfully "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateCategory(@PathVariable Integer id ,@Valid @RequestBody Category category , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.updateCategory(id,category);
        return ResponseEntity.status(200).body(new ApiResponse("category updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body(new ApiResponse("category deleted successfully"));
    }
    @GetMapping("/get-id/{name}")
    public ResponseEntity<?> getCategoryIdByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(categoryService.getCategoryIdByName(name));
    }


}

