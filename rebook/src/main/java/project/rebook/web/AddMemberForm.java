package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AddMemberForm {

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
