package toyproject.genshin.teybatguide.controller.dto.base;

import org.springframework.data.domain.Pageable;

public record PageResponseData<T>(T body, PageDto page) {

    public static <T> PageResponseData<T> of(T wrapper, PageDto page) {
        return new PageResponseData<T>(wrapper, page);
    }

    public static <T> PageResponseData<T> of(T wrapper, Pageable pageable) {
        return new PageResponseData<T>(wrapper, PageDto.of(pageable));
    }

}
