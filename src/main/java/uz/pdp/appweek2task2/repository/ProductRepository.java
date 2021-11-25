package uz.pdp.appweek2task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.pdp.appweek2task2.entity.PaymentType;
import uz.pdp.appweek2task2.entity.Product;
import uz.pdp.appweek2task2.projection.ProductProjection;

//@RepositoryRestResource(path = "product", collectionResourceRel = "list", excerptProjection = ProductProjection.class)
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
