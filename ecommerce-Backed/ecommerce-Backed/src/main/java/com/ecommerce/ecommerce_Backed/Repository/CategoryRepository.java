package com.ecommerce.ecommerce_Backed.Repository;

import com.ecommerce.ecommerce_Backed.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category ,Long> {


     @Query("select ct from Category ct  where ct.name=:topLevelCategory ")
    Category findByName(String topLevelCategory);


    @Query("Select c from Category c where c.name=:name and c.parentCategory.name=:parentCategory")
    Category findByNameAndParent(@Param("name") String name,@Param("parentCategory") String parentCategory);
}
