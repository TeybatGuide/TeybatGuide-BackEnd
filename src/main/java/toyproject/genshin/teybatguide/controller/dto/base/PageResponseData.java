package toyproject.genshin.teybatguide.controller.dto.base;

import org.springframework.data.domain.Pageable;

public record PageResponseData<T>(T body, PageDto page) {

    public static <T> PageResponseData<T> of(T body, PageDto page) {
        return new PageResponseData<T>(body, page);
    }

    public static <T> PageResponseData<T> of(T body, Pageable pageable) {
        return new PageResponseData<T>(body, PageDto.of(pageable));
    }

}
