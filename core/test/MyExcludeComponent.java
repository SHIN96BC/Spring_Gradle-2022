package hello.core.scan.filter;

import java.lang.annotation.*;

// #( 에노테이션 만들기 )
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    // 컴포넌트 스캔에서 제외
}
