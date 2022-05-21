package project.rebook.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class LoginForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
