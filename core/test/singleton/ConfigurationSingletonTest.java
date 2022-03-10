package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        // AppConfig 의 자바 코드를 보면 분명 memberRepository 가 new 생성으로 memberRepository 를
        // 3번 생성 했는데 3개 모두 같은 주소값이 나왔다. ( 순수 자바코드인데 싱글톤이 보장되었다?? 왜?? )
        // 그 이유는 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은
        // 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다. 그 임의의 다른 클래스가
        // 바로 싱글톤이 보장되도록 해준다. ( 우리는 결국 그 CGLIB 이라는, AppConfig 를
        // 상속받아 만든 클래스의 오버라이딩된 메소드를 호출하게 되는 것이다. )
        // !! 여기서 @Configuration 을 AppConfig 에 적용하지 않는다면 어떻게 될까??
        // 물론 @Configuration 없이도 @Bean 은 스프링 객체에 등록이 된다. 하지만 싱글톤은 보장이 될까?
        // @Configuration 을 적용하지 않으면 진짜로 내가 만든 AppConfig 가 스프링 객체에 등록이 된다.
        // 그러나 싱글톤은 깨지게 된다. ( 진짜 new 가 3번 호출 되는 것이다 ) ( 스프링이 관리하지 않는 객체가 되버리는 것이다 )
    }


    @Test
    void configuration() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
