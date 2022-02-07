package project.rebook.domain.member;

import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.member.Grade;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String nickname;

    private String loginId;

    private String password;

    private Grade grade;

    private int money;
}
