package uz.pdp.appweek2task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appweek2task2.entity.Feedback;
import uz.pdp.appweek2task2.entity.Product;

@Projection(types = Feedback.class)
public interface FeedbackProjection {
    Integer getId();
    String getFullName();
    String getEmail();
    String getText();
    Product getProduct();
}
