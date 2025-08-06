package com.example.blogsystem.Repository;
import com.example.blogsystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category ,Integer> {

    Category findUserById(Integer id);

    @Query("select u FROM Category u where u.id =?1")
    Category giveMeById(Integer id);


    @Query("SELECT c FROM Category c WHERE c.name =?1")
    Category findCategoryByName(String name);


}
