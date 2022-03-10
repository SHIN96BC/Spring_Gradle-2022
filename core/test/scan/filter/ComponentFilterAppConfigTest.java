package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }

    /* #( FilterType 의 옵션 )
            1) ANNOTATION: 기본값, 에노테이션을 인식해서 동작한다. ( 기본값이므로 생략 가능 )
            2) ASSIGNABLE_TYPE: 지정한 타입과 자식타입을 인식해서 동작한다.
            3) ASPECTJ: AspectJ 패턴 사용
                ex) org.example..*Service+
            4) REGEX: 정규 표현식
                ex) org\.example\.Default.*
            5) CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리 ( 직접 프로그래밍으로 조건을 만들 수 있다 )
                ex) org.example.MyTypeFilter

         cf) includeFilters 는 @Component 면 충분하기 때문에 별로 샤용할 일이 없다.
             excludeFilters 는 가끔 사용할 때가 있지만 많지는 않다.

         cf) 기본적으로 옵션을 변경하면서 사용하기 보다는 스프링의 기본 설정에 최대한 맞추어 시용하는 것이 좋다.
     */

}
