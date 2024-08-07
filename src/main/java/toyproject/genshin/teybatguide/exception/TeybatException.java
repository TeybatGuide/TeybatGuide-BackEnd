package toyproject.genshin.teybatguide.exception;

import lombok.Getter;
import toyproject.genshin.teybatguide.base.value.Code;

@Getter
public class TeybatException extends RuntimeException {

    private final Code errorCode;

    public TeybatException(String message) {
        super(message);
        this.errorCode = null;
    }

    public TeybatException(Code errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public TeybatException(Code errorCode, String message) {
        super(errorCode.getMessage(message));
        this.errorCode = errorCode;
    }

    public TeybatException(Code errorCode, String message, Throwable cause) {
        super(errorCode.getMessage(message), cause);
        this.errorCode = errorCode;
    }

    public TeybatException(Code errorCode, Throwable cause) {
        super(errorCode.getMessage(cause), cause);
        this.errorCode = errorCode;
    }

    public TeybatException(String message, Throwable cause) {
        super(Code.INTERNAL_ERROR.getMessage(message), cause);
        this.errorCode = Code.INTERNAL_ERROR;
    }

    public TeybatException(Throwable cause) {
        super(Code.INTERNAL_ERROR.getMessage(cause));
        this.errorCode = Code.INTERNAL_ERROR;
    }
}
