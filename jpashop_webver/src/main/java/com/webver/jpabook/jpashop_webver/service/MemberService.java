package com.webver.jpabook.jpashop_webver.service;

import com.webver.jpabook.jpashop_webver.domain.Member;
import com.webver.jpabook.jpashop_webver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //별다른 Annotaion 이 없는 메소드들은 다 읽기전용으로 사용해 빠르게.
@RequiredArgsConstructor // final 변수와 같이 초기화해야되는 애들만 생성자 만들어줌. @AllArgsConstructor, @NoArgsConstructor 의 중간쯤 되는 놈.
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     *  회원 가입
     */
    @Transactional // 쓰기가 필요한 경우만 @Transactional 로 명시해 쓰는게 성능적 이점.
    public Long join(Member member) {

        // 중복 가입 판단
        validateDuplicatedMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 중복 회원 가입 여부 판단 메소드
     * @param member
     */
    private void validateDuplicatedMember(Member member) {
        List<Member> foundMembers = memberRepository.findByName(member.getName());
        // 동명의 회원이 있다면 Exception 던짐
        if (!foundMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 한건 조회(id 로)
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
