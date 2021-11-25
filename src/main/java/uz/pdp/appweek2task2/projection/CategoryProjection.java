package uz.pdp.appweek2task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appweek2task2.entity.Category;

@Projection(types = Category.class)
public interface CategoryProjection {
    Integer getId();
    String getName();
    Boolean getActive();
}
