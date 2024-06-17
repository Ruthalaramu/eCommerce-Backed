package com.ecommerce.ecommerce_Backed.Repository;

import com.ecommerce.ecommerce_Backed.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p  "
            +" where (p.category.name=:category or :category='') and ((:minPrice is null  and :maxPrice is null) or" +
            " (p.discountPrice between :minPrice and  :maxPrice)) and (:minDiscount is null or p.discountPercent>=:minDiscount)" +
            " order by  case when :sort='price_low' then p.discountPrice end Asc," +
            " case when :sort='price_high' then p.discountPrice end desc")
    public List<Product>filterProducts(@Param("category") String category,
                                       @Param("minPrice") Integer minPrice,
                                       @Param("maxPrice") Integer maxPrice,
                                       @Param("minDiscount") Integer minDiscount,
                                       @Param("sort") String sort);
}
