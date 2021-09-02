package jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//==Item 클래스를 단독으로 클래스 만들일이 없다고 판단 -> 추상 클래스로 만듦==//
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //DB테이블 설정 전략을 싱글 테이블 전략으로 JOIN 전략으로도 사용가능. TABLE_PER_CLASS는 쓰지 말것
@DiscriminatorColumn //기본이 DTYPE으로 셋팅
public abstract class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID") //표기법_DB는 언더바, JAVA는 카멜케이스
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
