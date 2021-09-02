package jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;
}
