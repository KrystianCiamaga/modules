package kc.domain.service;/*
package krystianciamaga.com.demo.service;

import krystianciamaga.com.demo.entity.Asset;
import krystianciamaga.com.demo.entity.Product;

import krystianciamaga.com.demo.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {



    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts(int pageNumber,int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber,pageSize)).stream()
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductById(String id) {

        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));

        return product.get() ;}

    @Override
    public void addProduct(Product product) {


        productRepository.save(product);
    }

    @Override
    public void deleteProductById(String id) {

        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            throw new RuntimeException("Not found");
        }

    }

    @Override
    public void updateProductById(Product product,String id) {
     */
/*   Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));

        Product newProduct = ProductMapper.mapProductDtoToProduct(productDto);
        newProduct.setId(product.get().getId());

        productRepository.save(newProduct);*//*

    }
}
*/
