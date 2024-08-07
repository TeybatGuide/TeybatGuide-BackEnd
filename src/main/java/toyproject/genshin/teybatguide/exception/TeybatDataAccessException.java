package toyproject.genshin.teybatguide.exception;

import toyproject.genshin.teybatguide.base.value.Code;

public class TeybatDataAccessException extends TeybatException {

    public TeybatDataAccessException(String message) {
        super(Code.INTERNAL_ERROR, message);
    }

}
