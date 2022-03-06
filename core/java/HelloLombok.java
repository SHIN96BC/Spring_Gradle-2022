package hello.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 모든 필드의 겟터
@Setter // 모든 필드의 셋터
@AllArgsConstructor // 모든 필드를 넣은 생성자
@NoArgsConstructor // 기본 생성자
public class HelloLombok {
    private String name;
    private int age;
}
