package tspprocessproductservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tspprocessproductservice.dto.Product;
import tspprocessproductservice.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    ResponseEntity<Product> addProdcuct(@RequestBody @Valid Product product){
        String status = productService.addProduct(product);
        log.info("Product details added status - {}: "+ status);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/getProductList")
    List<Product> getProductList(){
        return productService.getProductList();
    }

    @GetMapping("/getProductList/{category}")
    List<Product> getProductsCategory(@PathVariable String category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/getProductById/{id}")
    Product getProductById(@PathVariable Integer id){
        return  productService.getProductBytId(id);
    }

    @PutMapping("/updateProduct")
    String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    String deleteProduct(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }

}
