package jpashop.domain;

import javax.persistence.*;

@Entity
public class Member_forteam { //양방향 매핑 실습용 Member객체

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    //@Column(name = "TEAM_ID") //외래키를 그대로 사용
    //private Long teamId;

    //==객체 연관관계를 사용하기 위함==//
    /***
     * 외래키가 있는 곳을 주인으로 정해라.
     * 1.DB입장에서 외래키가 있는 곳이 무조건 ManyToOne쪽이다. 즉, ( 다 : 1 로 연결하는 곳이 주인으로!!)
     * 2.성능입장에서 ManyToOne쪽을 주인으로 잡는 것이 좋다.
     * 3.중간에 일어날 수 있는 많은 버그를 잡을 수 있다.
     * **/
    @ManyToOne //해당 클래스를 기준으로 연관관계를 맺어줄 아이와 무슨 관계에 있는지 알려줘야 한다. ==> 현재 MEMBER : TEAM == N : 1 관계다. 즉 다대일 관계.
    @JoinColumn(name = "TEAM_ID") //해당 관계를 맺을 때 조인하는 컬럼이 뭔지 알려준다.  //이 두가지만 해주면 됨.
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    //==연관관계편의 메소드==//
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
