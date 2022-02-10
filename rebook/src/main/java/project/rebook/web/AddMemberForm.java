package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class AddMemberForm {

    @Size(min = 2, max = 8)
    private String nickname;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
