package project.rebook.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("비밀번호가 틀렸거나 존재하지 않는 사용자입니다.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
