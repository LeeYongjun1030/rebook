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
     * 한글, 영어 사용 가능
     * 숫자 사용 불가
     */
    @Size(min = 2, max = 8)
    private String nickname;

    /**
     * 로그인 아이디
     * 6~12자
     * 영문, 숫자
     */
    @NotEmpty
    private String loginId;

    /**
     * 비밀 번호
     * 6~12자
     * 영문, 숫자 반드시 포함
     */
    @NotEmpty
    @Pattern(regexp="[a-zA-Z1-9]{6,12}")
    private String password;

}
