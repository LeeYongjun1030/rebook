package project.rebook.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.rebook.domain.member.Grade;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String nickname;

    private String loginId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private int money;

    public Member(String nickname, String loginId, String password, Grade grade) {
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.grade = grade;
    }
}
