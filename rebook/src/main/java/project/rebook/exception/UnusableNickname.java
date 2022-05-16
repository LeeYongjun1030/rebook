package project.rebook.exception;

public class UnusableNickname extends RuntimeException{

    public UnusableNickname() {
        super("이미 존재하는 닉네임입니다.");
    }

    public UnusableNickname(String message) {
        super(message);
    }

    public UnusableNickname(String message, Throwable cause) {
        super(message, cause);
    }

    public UnusableNickname(Throwable cause) {
        super(cause);
    }
}
