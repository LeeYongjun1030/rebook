package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;


@Getter @Setter
@AllArgsConstructor
public class MemberDto {

    private String nickname;
    private int numberOfReviews;
    private Grade grade;

    public static MemberDto from(Member member) {
        return new MemberDto(member.getNickname(), member.getNumberOfReviews(), member.getGrade());
    }
}
