package toyproject.genshin.teybatguide.controller.dto.base;

import org.springframework.data.domain.Pageable;

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
