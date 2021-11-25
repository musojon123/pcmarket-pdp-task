package uz.pdp.appweek2task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.pdp.appweek2task2.entity.Feedback;
import uz.pdp.appweek2task2.entity.PaymentType;
import uz.pdp.appweek2task2.projection.PaymentTypeProjection;

//@RepositoryRestResource(path = "payment-type", collectionResourceRel = "list", excerptProjection = PaymentTypeProjection.class)
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
