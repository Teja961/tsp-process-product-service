package tspprocessproductservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tspprocessproductservice.dto.Product;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    @Query("{'Category.name':?0}")
    List<Product> getProductsByCategory(String category);
}
