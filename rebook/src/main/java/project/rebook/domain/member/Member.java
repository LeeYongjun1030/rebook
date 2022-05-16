package project.rebook.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.*;

@Entity
@Getter
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

    /**
     * 비즈니스 로직
     */

    // 리뷰 개수 증가
    public void increaseNumberOfReviews() {
        this.numberOfReviews += 1;
        updateGrade();
    }

    // 리뷰 개수 감소
    public void decreaseNumberOfReviews(int count) {
        this.numberOfReviews -= count;
        updateGrade();
    }

    // 등급 업데이트
    public void updateGrade(){
        if (this.numberOfReviews >= GradeConst.NUM_of_REVIEWS_to_VIP) {
            this.grade = Grade.VIP;
        }else{
            this.grade = Grade.NORMAL;
        }
    }

    // 아이디와 비밀번호 체크
    public boolean verify(String loginId, String password, PasswordEncoder passwordEncoder) {
        return this.loginId.equals(loginId) && passwordEncoder.matches(password, this.password);
    }
}
