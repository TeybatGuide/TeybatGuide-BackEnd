package toyproject.genshin.teybatguide.base;

import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.base.dto.PageDto;

public record PageResponseData<T>(T wrapper, String message, PageDto page) {

    public static <T> PageResponseData<T> of(T wrapper, String message, PageDto page) {
        return new PageResponseData<T>(wrapper, message, page);
    }

    public static <T> PageResponseData<T> of(T wrapper, PageDto page) {
        return new PageResponseData<T>(wrapper, null, page);
    }

    public static <T> PageResponseData<T> of(T wrapper, String message, Pageable pageable) {
        return new PageResponseData<T>(wrapper, message, PageDto.of(pageable));
    }

    public static <T> PageResponseData<T> of(T wrapper, Pageable pageable) {
        return new PageResponseData<T>(wrapper, null, PageDto.of(pageable));
    }

}
