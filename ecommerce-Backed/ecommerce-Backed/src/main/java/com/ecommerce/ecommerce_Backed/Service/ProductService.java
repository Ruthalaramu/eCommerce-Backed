package com.ecommerce.ecommerce_Backed.Service;

import com.ecommerce.ecommerce_Backed.DTO.CreateProductRequest;
import com.ecommerce.ecommerce_Backed.Exception.ProductException;
import com.ecommerce.ecommerce_Backed.IMPL.ProductServiceImpl;
import com.ecommerce.ecommerce_Backed.Model.Category;
import com.ecommerce.ecommerce_Backed.Model.Product;
import com.ecommerce.ecommerce_Backed.Repository.CategoryRepository;
import com.ecommerce.ecommerce_Backed.Repository.ProductRepository;
import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.List.of;

@Service
public class ProductService implements ProductServiceImpl {

    @Autowired
    private ProductRepository  productRepository;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private CategoryRepository  categoryRepository;
    @Override
    public ResponseEntity<Product> createProduct(CreateProductRequest req)throws Exception {
        try {
            Product savedProduct = null;
            Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());
            if (topLevel == null) {
                Category toplevelCategory = new Category();
                toplevelCategory.setName(req.getTopLevelCategory());
                toplevelCategory.setLevel(1);
                toplevelCategory = categoryRepository.save(toplevelCategory);
            }
            Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());
            if (secondLevel == null) {
                Category secondLevelCategory = new Category();
                secondLevelCategory.setName(req.getSecondLevelCategory());
                secondLevelCategory.setLevel(2);
                secondLevelCategory = categoryRepository.save(secondLevelCategory);
            }
            Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());

            if (thirdLevel == null) {
                Category thirdLevelCategory = new Category();
                thirdLevelCategory.setName(req.getThirdLevelCategory());
                thirdLevelCategory.setLevel(3);
                thirdLevelCategory = categoryRepository.save(thirdLevelCategory);

                Product product = new Product();
                product.setTitle(req.getTitle());
                product.setPrice(req.getPrice());
                product.setBrand(req.getBrand());
                product.setColor(req.getColor());
                product.setDiscountPercent(req.getDiscountPercent());
                product.setImageUrl(req.getImageUrl());
                product.setQuantity(req.getQuantity());
                product.setSize(req.getSize());
                product.setCategory(thirdLevel);
                product.setDescription(req.getDescription());
                product.setDiscountPrice(req.getDiscountPrice());
                product.setCreatedDate(LocalDate.now());
                savedProduct = productRepository.save(product);
            }
            return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public ResponseEntity<String>  deleteProduct(Long productId) throws ProductException {
        Product product=findProductById(productId);
        product.getSize().clear();
        productRepository.delete(product);
        return new ResponseEntity<>("product deleted",HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Product> productUpdate(Long productId, Product req) throws ProductException {
        Product updateProduct;
        try {
            Product product = findProductById(productId);
            if (req.getQuantity() > 0) {
                product.setQuantity(req.getQuantity());
            }
            updateProduct = productRepository.save(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return new ResponseEntity<Product>(updateProduct, HttpStatus.CREATED);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product>product=productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new ProductException("Product Not Found with id-"+id);
    }

    @Override
    public ResponseEntity<List<Product>> findProductByCategory(String category) throws ProductException {
        return null;
    }

    @Override
    public ResponseEntity<Page<Product>> getAllProduct(String category, List<String> color, List<String> sizes,
                                               Integer minPrice, Integer maxPrice, Integer minDiscount, String sort,
                                                       String stock, Integer pageNumber, Integer pageSize) throws Exception{

        try {
            Pageable pageable= PageRequest.of(pageNumber,pageSize);
            List<Product> product=productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
            if(!color.isEmpty() && color!=null){
                product =  product.stream().filter(p->color.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
            }
            if(!stock.isEmpty() && stock!=null){
                if(stock.equals("in_stock")){
                    product =  product.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
                } else if (stock.equals("out_of_stock")) {
                    product =product.stream().filter((p->p.getQuantity()<1)).collect(Collectors.toList());
                }
            }
            int startIndex= (int) pageable.getOffset();
            int endIndex=Math.max(startIndex+pageable.getPageSize(),product.size());
            List<Product> pageContent=product.subList(startIndex,endIndex);
            Page<Product>filteredProducts=new PageImpl<>(pageContent,pageable,product.size());

            return new ResponseEntity<>(filteredProducts,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
}
