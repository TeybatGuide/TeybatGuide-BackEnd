package toyproject.genshin.teybatguide.exception;

import toyproject.genshin.teybatguide.base.value.Code;

public class TeybatBadRequestException extends TeybatException {

    public TeybatBadRequestException(String message) {
        super(Code.BAD_REQUEST, message);
    }
}
