package kc.domain.controller;


import kc.domain.entity.Product;
import kc.domain.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {


    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }
    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }



    /*@GetMapping("{pageNumber}/{pageSize}")
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

   oductService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public void updateProductById(@RequestBody Product productDto,@PathVariable String id){
        productService.updateProductById(productDto,id);
    }*/




}
