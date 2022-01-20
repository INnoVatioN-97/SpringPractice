package hellojpa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 앵간하면 다대다는 @ManyToMany 는 JPA 가 지원은 하지만 앵간하면
 * @ManyToMany 로 쓰지 말라고 영한센세께서 말씀하심.
 * 머가리 빠개진다고.
 * 그래서 M:N 을 1:N N:1 로 쪼개서 만드는 형태로 구현.
 */
@Entity
public class MemberProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;

    private LocalDateTime orderDateTime;

}
