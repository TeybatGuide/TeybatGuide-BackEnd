package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Materials {

    CHARACTER_EXP_MATERIAL("캐릭터 경험치 소재"),
    CHARACTERS_TALENT_MATERIAL("캐릭터 특성 소재"),
    CHARACTER_ASCENSION_MATERIAL("캐릭터 돌파 소재"),
    CHARACTERS_ENHANCEMENT_MATERIAL("캐릭터 육성 소재"),
    CHARACTERS_WEAPONS_ENHANCEMENT_MATERIAL("캐릭터와 무기 육성 소재"),
    WEAPONS_ASCENSION_MATERIAL("무기 돌파 소재"),
    LOCAL_SPECIALTY("특산물"),
    OTHER("-");

    private final String resourceByName;

}
