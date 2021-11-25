package uz.pdp.appweek2task2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    private Category category;

    @OneToOne
    private VideoLink videoLink;

    @Column
    private String brandName;

    @Column
    private String specifications;

    @Column
    private String description;

    @OneToOne
    private Attachment attachment;

    @Column
    private Date madeOn;

    @ManyToOne
    private PaymentType paymentType;

    @Column
    private Boolean active;
}
