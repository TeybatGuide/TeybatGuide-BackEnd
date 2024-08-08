package toyproject.genshin.teybatguide.base;

import lombok.Getter;
import toyproject.genshin.teybatguide.base.dto.ResponseDto;
import toyproject.genshin.teybatguide.base.value.Code;

@Getter
public class ResponseData<T> extends ResponseDto {

    private final T wrapper;

    private ResponseData(T wrapper) {
        super(Code.OK.toString(), Code.OK.getMessage());
        this.wrapper = wrapper;
    }

    private ResponseData(String message, T wrapper) {
        super(Code.OK.toString(), message);
        this.wrapper = wrapper;
    }

    public static <T> ResponseData<T> of(String message, T wrapper) {
        return new ResponseData<>(message, wrapper);
    }

    public static <T> ResponseData<T> of(String message) {
        return new ResponseData<>(message, null);
    }

    public static <T> ResponseData<T> of(T wrapper) {
        return new ResponseData<>(wrapper);
    }
}
