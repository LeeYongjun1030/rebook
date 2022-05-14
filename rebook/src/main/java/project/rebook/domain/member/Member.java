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

    private int numberOfReviews;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Member(String nickname, String loginId, String password, int numberOfReviews, Grade grade) {
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.numberOfReviews = numberOfReviews;
        this.grade = grade;
    }
}
