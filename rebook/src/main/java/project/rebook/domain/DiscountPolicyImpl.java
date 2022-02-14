package project.rebook.domain;

import org.springframework.stereotype.Component;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;

@Component
public class DiscountPolicyImpl implements DiscountPolicy{

    /**
     * VIP 고객의 경우 10프로 할인
     */
    @Override
    public int discount(Member member, int totalPrice) {
        Grade memberGrade = member.getGrade();
        if (memberGrade == Grade.VIP) {
            return (int)(totalPrice * 0.9);
        } else {
            return totalPrice;
        }
    }

}
