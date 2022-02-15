package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
public class AddMemberForm {

    /**
     * 닉네임
     * 2~8자
     * 한글 사용 가능
     */
    @Pattern(regexp="[가-힣]{2,8}")
    private String nickname;

    /**
     * 로그인 아이디
     * 6~12자
     * 영문, 숫자
     */
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z]).{6,12}$")
    private String loginId;

    /**
     * 비밀 번호
     * 6~12자
     * 영문, 숫자
     */
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,12}$")
    private String password;

}
