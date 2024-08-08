package toyproject.genshin.teybatguide.base;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.base.dto.ResponseDto;
import toyproject.genshin.teybatguide.base.value.Code;
import toyproject.genshin.teybatguide.exception.TeybatException;

public class ErrorResponseData extends ResponseDto {

    private ErrorResponseData(@NotNull Code errorCode) {
        super(errorCode.toString(), errorCode.getMessage());
    }

    private ErrorResponseData(@NotNull Code errorCode, String message) {
        super(errorCode.toString(), message);
    }

    private ErrorResponseData(@NotNull Code errorCode, @NotNull TeybatException exception) {
        super(errorCode.toString(), exception.getMessage());
    }

    @Contract("_ -> new")
    public static @NotNull ErrorResponseData of(Code errorCode) {
        return new ErrorResponseData(errorCode);
    }

    @Contract("_, _ -> new")
    public static @NotNull ErrorResponseData of(Code errorCode, String message) {
        return new ErrorResponseData(errorCode, message);
    }

    @Contract("_, _ -> new")
    public static @NotNull ErrorResponseData of(Code errorCode, TeybatException exception) {
        return new ErrorResponseData(errorCode, exception);
    }
}
