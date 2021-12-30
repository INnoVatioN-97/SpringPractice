package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){

        //이렇게 되면 MemberService 클래스에서 기존에 리포지토리를 관리하고 있던 형태가 아니라
        // 생성자로 서비스가 생성될 때 리포지토리를 초기화해주는 의존성 주입의 성격을 띄게 된다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void join() {
        /**
         * 이런 테스트할 떄 개꿀 문법이 바로 given-when-then 문법.
         * 뭔가가 주어지면(given) 이걸 실행했을 때(when) 결과가 이게 나와야 해(then)
         */
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member foundMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    //만약 중복회원가입시 validating 이 제대로 되는지 시험
    @Test
    public void checkDuplicateJoining() {
        //given
        Member mem1 = new Member();
        mem1.setName("spring");

        Member mem2 = new Member();
        mem2.setName("spring");

        //when
        memberService.join(mem1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(mem2);
        });

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원이름 입니다.");
        //아래코드와 기능 동일. 위에 코드는 "~~~"라는 예외가 발생되야해, ()->{이 내용이 돌아가면} 라는 느낌

        /*try {
            memberService.join(mem2);
            fail();
        } catch (IllegalStateException e) {
            // 중복 발생시 throw 되는 예외속 메시지와 얘가 catch 한 메시지 내용이 같은지.
            // 같으면 테스트 결과에 이상없음으로 나온다.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원이름 입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}