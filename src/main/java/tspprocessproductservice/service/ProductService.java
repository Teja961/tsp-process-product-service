package tspprocessproductservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import tspprocessproductservice.dto.Category;
import tspprocessproductservice.dto.Product;
import tspprocessproductservice.exception.OfferNotFoundException;
import tspprocessproductservice.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product product) {

        if(product.getPrice() == 0 && product.getDiscount() >0){
            throw new OfferNotFoundException("Offer Not allowed for 0 price Products");
        }
         productRepository.save(product);
         return "Product details added Successfully";
        }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {

        return productRepository.getProductsByCategory(category);
    }


    public Product getProductBytId(Integer id) {
        return productRepository.findById(id).get();
    }

    public String updateProduct(Product product) {
        productRepository.save(product);
        return "Product Updated successfully";
    }
    public String deleteProductById(Integer id) {
        productRepository.deleteById(id);
        return "Product Deleted Successfully";
    }
}
