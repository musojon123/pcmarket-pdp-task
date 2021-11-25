package uz.pdp.appweek2task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appweek2task2.entity.PaymentType;

@Projection(types = PaymentType.class)
public interface PaymentTypeProjection {
    Integer getId();
    String getName();
    Boolean getActive();

}
