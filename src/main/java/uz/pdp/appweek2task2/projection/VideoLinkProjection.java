package uz.pdp.appweek2task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appweek2task2.entity.VideoLink;

@Projection(types = VideoLink.class)
public interface VideoLinkProjection {
    Integer getId();
    String getVideoLink();
    Boolean getActive();
}
