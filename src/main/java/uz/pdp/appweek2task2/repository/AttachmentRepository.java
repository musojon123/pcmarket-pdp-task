package uz.pdp.appweek2task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appweek2task2.entity.Attachment;
import uz.pdp.appweek2task2.projection.AttachmentProjection;

//@RepositoryRestResource(path = "attachment", collectionResourceRel = "list", excerptProjection = AttachmentProjection.class)
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
