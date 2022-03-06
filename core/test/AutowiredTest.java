package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
//        ( 옵션 처리 )
//        1번 방법 ( required = false 로 해놨을때 의존관계가 아에 없으면 메서드 자체가 호출이 안된다 )
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

//        2번방법 ( 자동 주입할 대상이 없을때 호출은 되지만 null 이 입력된다 )
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

//        3번방법 ( 스프링 빈이 없을때 호출은 되지만 Optional.empty 가 입력된다 )
        @Autowired
        public void setBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }


    }


}
