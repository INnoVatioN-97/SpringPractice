package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * MemberRepository 와 같은 인터페이스단에는 개발자가 알기 쉽게 이름을 대충 적지만,
 * 지금과 같은 Service class 에는 좀 더 비즈니스적으로 join, findMembers 와 같이
 * 직접적 명시를 해주어 나중에 다른사람들과 소통할때 좀 더 편하도록 한다.
 */

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // cmd + option + V 하면 해당 메소드의 리턴값을 받는 변수를 만들어줌.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m-> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이름 입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(long memberId){
        return memberRepository.findById(memberId);
    }
}
