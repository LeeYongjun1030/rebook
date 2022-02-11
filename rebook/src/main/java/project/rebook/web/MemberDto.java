package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.member.Grade;


@Getter @Setter
public class MemberDto {

    private String nickname;
    private Grade grade;
}
