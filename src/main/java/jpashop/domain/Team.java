package jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID") //외래키를 그대로 사용
    private Long id;
    private String name;

    /**양방향 매핑을 하기 위함 -- 주의 toString쓰지 말 것!!! 무한루프 돌게 된다.**/
    @OneToMany(mappedBy = "team") //해당 클래스를 기준으로 연관관계를 맺어줄 아이와 무슨 관계에 있는지 알려줘야 한다. 
    // + (연관관계의 주인이 아닌 애, 읽기만 가능한 애는)매핑되는 다른 테이블에 어떤 칼럼과 연결되어 있는지 알려주기 "mappedBy" 
    private List<Member_forteam> members = new ArrayList<>(); //==관례로, ArrayList로 초기화 시켜 null포인터가 뜨는 것을 방지한다.==//

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

    public List<Member_forteam> getMembers() {
        return members;
    }

    public void setMembers(List<Member_forteam> members){
        this.members = members;
    }
}
