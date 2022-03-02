package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        /* 상태를 유지하는 설계
        // ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회 ( 기대값은 10000원 이지만 결과값은 20000원이 나온다)
        // 싱글톤으로 하나의 객체를 공유하고있고 그 객체안에 공유필드 price가 계속 유지되고 있기 때문에 발생하는 문제점이다.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
         */


        // 무상태 설계
        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        System.out.println("userAPrice = " + userAPrice);

       // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}