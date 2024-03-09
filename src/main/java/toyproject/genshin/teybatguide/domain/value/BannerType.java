package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BannerType {

    CHARACTER("캐릭터"),
    WEAPON("무기"),
    CHRONICLED_CHARACTER("묶음 기원 - 캐릭터"),
    CHRONICLED_WEAPON("묶음 기원 - 무기");

    private final String bannerTypeName;

}
