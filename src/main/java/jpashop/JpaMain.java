package jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        /**JPA는 하나의 DB당 하나의 EntityManagerFactory를 만들어야 한다.**/
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        /**고객의 요청이 올 때마다 EntityManager를 통해서 작업을 해야한다.**/
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //code

        /**==jpa을 통한 모든 작업은 꼭 트랜젝션 단위로 해당 트랜젝션 안에서 작업해야 하기 때문에 작업을 하나의 트랜젝션으로 감싸주어야 한다.
         * 즉, JPA의 모든 데이터 변경은 트랜젝션 안에서 실행해야한다.==**/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); //트랜젝션 시작

        try {
            transaction.commit();
        }catch (Exception e){
            //문제가 발생 하면 catch문에서 예외처리로 트랜젝션 롤백해주기.
            transaction.rollback();
        }finally {
            //다 쓰면 닫아줘야 함.
            entityManager.close();
        }
        //전체가 끝나면 다 쓰면 entityManagerFactory 닫아줘야 함.
        entityManagerFactory.close(); //커넥션 풀링을 위해.

    }


/**
 *  매핑 실습용 app
 *  **/
//    public static void main(String[] args) {
//        /**JPA는 하나의 DB당 하나의 EntityManagerFactory를 만들어야 한다.**/
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
//
//        /**고객의 요청이 올 때마다 EntityManager를 통해서 작업을 해야한다.**/
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        //code
//
//        /**==jpa을 통한 모든 작업은 꼭 트랜젝션 단위로 해당 트랜젝션 안에서 작업해야 하기 때문에 작업을 하나의 트랜젝션으로 감싸주어야 한다.
//         * 즉, JPA의 모든 데이터 변경은 트랜젝션 안에서 실행해야한다.==**/
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin(); //트랜젝션 시작
//
//        try {
//            //팀 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            entityManager.persist(team);
//            //회원 저장
//            Member_forteam member = new Member_forteam();
//            member.setName("member1");
//            member.changeTeam(team); //setTeam대신에 연관관계편의 메소드로 셋팅.단방향 연관관계 설정, 참조 저장 (객체를 테이블에 맞춰 모델링 했던 코드와 달리 이렇게 바로 객체를 넣어 연결가능하다.)
//            entityManager.persist(member);
//
//            /***
//             * 만일 Member를 먼저 저장하고ㅡ team객체의 arraylist에 member를 저장하려고 하면 쿼리가 날라가지 않는다.
//             * ==>team은 연관관계의 주인이 아니기 때문!!
//             *
//             * 예컨데))
//                 *  Team team = new Team();
//                 *  team.setName("TeamA");
//                 *  em.persist(team);
//                 *
//                 *  Member member = new Member();
//                 *  member.setName("member1");
//                 *
//                 *  //역방향(주인이 아닌 방향)만 연관관계 설정
//                 *  team.getMembers().add(member);
//                 *
//                 *  em.persist(member);
//             *
//             *  이런식으로 코딩하면 주인이 아닌 방향으로 연관관계를 설정한 것이므로 값이 들어가지 않는다.(null이들어감)
//             *
//             *  그러나!! 순수객체의 상태를 고려해서 항상 양쪽에 값을 설정해줘야 한다. 즉, 주인방향으로 연관관계 코드를 작성해도
//             *   team.getMembers().add(member); 이 코드를 추가로 넣어주어 주인이 아닌 방향에도 값을 추가로 셋팅 해줘야 하는 것.
//             *   왜냐하면, 아래의 이유들 때문이다.
//             *           1. 만일 entityManager.flush(); entityManager.clear();로 캐싱의 저장공간을 비워주지 않는다면
//             *               jpa가 연관관계를 설정하여 team과 member를 맞춰주기 전에 이미 캐싱에 List<Member>에 member가 매핑 되지
//             *               않은 채로 올라가서 이걸 이후에 계속 쓰려고 하기 때문에 오류.
//             *           2. test코드를 작성할 때는 jpa를 제외하고 순수 java로 코딩하므로 이때도 빈 List<Member>를 가지고 test 하게 됨
//             *
//             *   따라서, 연관관계 편의 메소드를 생성하여 changeTeam을 할 때 team.getMembers().add(member);이 코드가 항상 함께 실행 되도록 설정하자.
//             * **/
//
//            //조회
//            Member_forteam findMember = entityManager.find(Member_forteam.class, member.getId());
//            //참조를 사용해서 연관관계 조회
//            Team findTeam = findMember.getTeam(); //객체를 테이블에 맞춰 모델링 했던 코드와 달리 이렇게 바로 해당 member의 소속 팀을 찾을 수 있다.
//
//            transaction.commit();
//        }catch (Exception e){
//            //문제가 발생 하면 catch문에서 예외처리로 트랜젝션 롤백해주기.
//            transaction.rollback();
//        }finally {
//            //다 쓰면 닫아줘야 함.
//            entityManager.close();
//        }
//        //전체가 끝나면 다 쓰면 entityManagerFactory 닫아줘야 함.
//        entityManagerFactory.close(); //커넥션 풀링을 위해.
//
//    }
}
