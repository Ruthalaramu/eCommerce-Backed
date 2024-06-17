package com.ecommerce.ecommerce_Backed.Controller;

import com.ecommerce.ecommerce_Backed.DTO.CreateProductRequest;
import com.ecommerce.ecommerce_Backed.Exception.ProductException;
import com.ecommerce.ecommerce_Backed.IMPL.ProductServiceImpl;
import com.ecommerce.ecommerce_Backed.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ecommerce")
public class ProductController {
    private ProductServiceImpl productService;

    @PostMapping("/admin/createProduct")
  public ResponseEntity<Product> createAdminProduts(@RequestBody CreateProductRequest request) throws Exception {
       return productService.createProduct(request);

  }
    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductException {
        Product product=productService.findProductById(productId);
        return new ResponseEntity<>(product,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
                                                                      @RequestParam List<String> color, @RequestParam List<String> size, @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort, @RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws Exception {
        Page<Product> res= (Page<Product>) productService.getAllProduct(category,color,size,minPrice,
                maxPrice,minDiscount,sort,stock,pageNumber,pageSize);
        System.out.println("complete products"+res);

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    @GetMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam Long productId) throws ProductException {
        return productService.deleteProduct(productId);
    }
    @GetMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Long productId, @RequestBody Product product) throws ProductException {
        return productService.productUpdate(productId,product) ;
    }


}
