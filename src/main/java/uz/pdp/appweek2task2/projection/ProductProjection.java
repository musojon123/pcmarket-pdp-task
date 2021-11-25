package uz.pdp.appweek2task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appweek2task2.entity.Attachment;
import uz.pdp.appweek2task2.entity.Category;
import uz.pdp.appweek2task2.entity.Product;
import uz.pdp.appweek2task2.entity.VideoLink;

import java.util.Date;

@Projection(types = Product.class)
public interface ProductProjection {
    Integer getId();
    String getName();
    Category getCategory();
    VideoLink getVideoLink();
    String getBrandName();
    String getSpecifications();
    String getDescription();
    Attachment getAttachment();
    Date getMadeOn();
}
