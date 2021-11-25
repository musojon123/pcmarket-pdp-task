package uz.pdp.appweek2task2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appweek2task2.entity.Attachment;
import uz.pdp.appweek2task2.entity.PaymentType;

import java.util.Date;
import java.util.Set;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductT {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String brandName;
    private String specifications;
    private String description;
    private Integer attachmentId;
    private Date madeOn;
    private Integer paymentTypeId;
    private Boolean active;
}
