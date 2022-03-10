package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("memberService2") // 기본적으로 클래스 이름에서 맨 앞글자만 소문자가 된 상태로 스프링빈에 등록되는데,
// 임의로 이름을 지정하고 싶은 경우 () 안에 사용할 이름을 적어주면 된다. ( 특별한 경우에만 사용 )
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById((memberId));
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
