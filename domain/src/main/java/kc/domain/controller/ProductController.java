package kc.domain.controller;/*
package krystianciamaga.com.demo.controller;


import krystianciamaga.com.demo.entity.Product;
import krystianciamaga.com.demo.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {


    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @GetMapping("{pageNumber}/{pageSize}")
    public List<Product> getAllProducts(@PathVariable("pageNumber") final Integer pageNumber, @PathVariable("pageSize") final Integer pageSize){
        return productService.findAllProducts(pageNumber,pageSize);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public void removeProductById(@PathVariable String id){
        productService.deleteProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product productDto){
        productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public void updateProductById(@RequestBody Product productDto,@PathVariable String id){
        productService.updateProductById(productDto,id);
    }




}
*/
