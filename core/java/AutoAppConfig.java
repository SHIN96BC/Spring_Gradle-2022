package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages 의 디폴트값( 지정하지 않았을 때 ): @ComponentScan 이 붙은 클래스의 패키지를 탐색위치로 지정한다.
//        basePackages = "hello.core.member",  // 패키지의 탐색위치를 지정해주는 것
//        basePackageClasses = AutoAppConfig.class, // 클래스의 패키지를 탐색위치로 지정하는 것
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @ComponentScan 은 @Component 가 붙은 클래스를 스캔해서 스프링빈에 자동으로 등록해준다. ( 스프링빈에 등록하고 싶지 않은 것은 excludeFilters 에 써준다 )
// @Configuration 이 스캔 대상이 된 이유도 Configuration 소스코드를 열어보면 @Component 가 붙어있기 때문이다.
// 실무에서는 굳이 위의 예제처럼 잘 안한다.
public class AutoAppConfig {

    /*
     #( 스프링 자동 빈 등록과 수동으로 등록한 빈이 충돌 할 떄 )
          이런경우에는 수동빈이 우선권을 가진다. ( 수동빈이 자동빈을 오버라이딩 해버린다 )
          문제점)
            물론 개발자가 의도적으로 이런 결과를 기대했다면, 저동 보다는 수동이 우선권을 가지는 것이 좋다.
            하지만 현실은 개발자가 의도적으로 설정해서 이런 결과가 만들어지기 보다는 여러 설정들이 꼬여서 이런 결과가
            만들어지는 경우가 대부분이다. 이런 버그는 정말 잡기 어려운 버그이다.
            ( 그래서 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생 하도록 기본 값을 바꾸었다.)
            ( ***중요***
              코딩을 할때는 최대한 애매한 상황은 만들지 않는 것이 가장 좋다 명확하지않으면 하지않는 것이 좋다
              아무리 코드가 더 짧아지고 이뻐진다고해도, 코드가 명확하지 않거나 애매할떄는 코드가 조금 길어지고 같은 코드가 중복될지라도
              한눈에 잘 들어오고 명확한 코드를 선택하는 것이 좋을떄가 훨씬 많다
            )
     */
    /*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}

/*
 #( @ComponentScan 의 기본 대상 )
    1) @Component: 컴포넌트 스캔에서 사용
    2) @Controller: 스프링 MVC 컨트롤러에서 사용 ( 스프링 MVC 컨트롤러로 인식 )
    3) @Service: 스프링 비지니스 로직에서 사용  ( 사실 서비스 에노테이션은 특별한 처리를 하지않는다. 대신 개발자들이 핵심 비지니스 로직이 여기에 있겠구나 하고 인지하는데 도움이 된다 )
    4) @Repository: 스프링 데이터 접근 계층에서 사용 ( 스프링 데이터 접근계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다. )
    5) @Configuration: 스프링 설정 정보에서 사용 ( 스프링 설정정보를 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 해준다. )

   중요한점은 에노테이션은 상속관계라는 것이 없다. 그래서 특정 에노테이션이 붙어있는 것을 인식할 수
   있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능이다.

   cf) useDefaultFilters 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.
 */
