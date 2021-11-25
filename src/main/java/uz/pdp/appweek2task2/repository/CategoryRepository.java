package uz.pdp.appweek2task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appweek2task2.entity.Category;

/*
@RepositoryRestResource(path = "category", collectionResourceRel = "list" ,excerptProjection = FeedbackProjection.class)
*/
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
