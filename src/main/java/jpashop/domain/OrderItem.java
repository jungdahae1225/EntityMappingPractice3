package jpashop.domain;

import javax.persistence.*;

@Entity
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    //@Column(name = "ORDER_ID")
    //private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY) //현재 OrderItem테이블과 Order테이블은 다대일관계 -> 추후 Order테이블에서 반대로 OrderItem테이블을 참고하는게 비지니스적으로 필요성이 있다고 판단되어 양방향으로 만들거임
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    //@Column(name = "ITEM_ID")
    //private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)//현재 OrderItem테이블과 Item테이블은 다대일관계
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
