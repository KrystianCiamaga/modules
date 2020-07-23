package kc.domain.service;





import kc.domain.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {



    //List<Product> findAllProducts(int pageNumber, int pageSize);
    Product findProductById(String id) throws IOException;
    void saveProduct(Product product);
    //void deleteProductById(String id);
    //void updateProductById(Product productDto,String id);


}
