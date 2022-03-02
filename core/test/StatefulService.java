package hello.core.singleton;

public class StatefulService {
    // 매우중요!! 실무에서 아주 많이 나오는 예시이다. ( 싱글톤 패턴의 문제점 )

    /*  상태를 유지하는 설계
    private int price;  //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price;  // 여기가 문제!!
    }

    public int getPrice() {
        return price;
    }
    */


    // 무상태 설계
    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        return price;  // 여기가 문제!!
    }

}
