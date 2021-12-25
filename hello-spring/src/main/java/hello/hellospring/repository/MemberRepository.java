package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    /*
    * Optional : java 8 부터 도입된 기능.
    * 만약 id, name등으로 객체를 찾아오라고 시켰는데 없을 경우 null이 반환될텐데,
    * 이 null을 Optional로 감싸서 처리할 수 있도록 할 수 있다.
    * */
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
