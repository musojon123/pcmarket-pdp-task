package uz.pdp.appweek2task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.pdp.appweek2task2.entity.Feedback;
import uz.pdp.appweek2task2.projection.FeedbackProjection;

//@RepositoryRestResource(path = "feedback", collectionResourceRel = "list", excerptProjection = FeedbackProjection.class)
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
