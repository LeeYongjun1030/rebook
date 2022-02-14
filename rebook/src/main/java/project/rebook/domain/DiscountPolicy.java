package project.rebook.domain;

import project.rebook.domain.member.Member;

public interface DiscountPolicy {
    public int discount(Member member, int totalPrice);
}
