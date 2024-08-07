package toyproject.genshin.teybatguide.base.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.base.value.Code;

@Getter
@RequiredArgsConstructor
public class ResponseDto {

    private final String code;
    private final String message;

    public static ResponseDto of(Code code, String message) {
        return new ResponseDto(code.toString(), message);
    }

    public static ResponseDto of(Code code, Exception e) {
        return new ResponseDto(code.toString(), e.getMessage());
    }

}
