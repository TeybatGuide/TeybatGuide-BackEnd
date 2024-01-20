package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Stars {

    FIVE("5성"),
    FOUR("4성"),
    THREE("3성");

    private final String starsName;

}