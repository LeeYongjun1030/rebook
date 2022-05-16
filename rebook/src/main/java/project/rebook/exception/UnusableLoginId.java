package project.rebook.exception;

public class UnusableLoginId extends RuntimeException{

    public UnusableLoginId() {
        super("이미 존재하는 아이디입니다.");
    }

    public UnusableLoginId(String message) {
        super(message);
    }

    public UnusableLoginId(String message, Throwable cause) {
        super(message, cause);
    }

    public UnusableLoginId(Throwable cause) {
        super(cause);
    }
}
