package uz.pdp.appweek2task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appweek2task2.entity.Attachment;

@Projection(types = Attachment.class)
public interface AttachmentProjection {
    Integer getId();
    String getName();
    Long getSize();
    String getContentType();

}
