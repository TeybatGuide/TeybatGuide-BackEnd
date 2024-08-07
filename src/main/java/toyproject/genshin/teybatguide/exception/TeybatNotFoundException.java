package toyproject.genshin.teybatguide.exception;

import toyproject.genshin.teybatguide.base.value.Code;

public class TeybatNotFoundException extends TeybatException {

    public TeybatNotFoundException(String message) {
        super(Code.NOT_FOUND, message);

    }
}
