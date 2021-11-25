package uz.pdp.appweek2task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.pdp.appweek2task2.entity.Product;
import uz.pdp.appweek2task2.entity.VideoLink;
import uz.pdp.appweek2task2.projection.VideoLinkProjection;

//@RepositoryRestResource(path = "video-link", collectionResourceRel = "list", excerptProjection = VideoLinkProjection.class)
@Repository
public interface VideoLinkRepository extends JpaRepository<VideoLink, Integer> {
    boolean existsByVideoLink(String videoLink);
    boolean existsByVideoLinkAndIdNot(String videoLink, Integer id);
}
