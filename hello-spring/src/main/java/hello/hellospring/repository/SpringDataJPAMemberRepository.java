package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * SpringDataJPA 는 JpaRepository 인터페이스를 상속받아 사용. <사용할 엔티티, 식별자 타입>
 * 스프링 데이터 JPA 방식에서는 이정도의 설정만 해두고 config 클래스에서 부가 설정만 해주면 된다.
 * 이렇게 인터페이스에 리포지토리와 JpaRepository 를 상속하면 Spring 자체적으로 인터페이스를 기반으로
 * 자동으로 구현체를 만들어준다.
 */
public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
