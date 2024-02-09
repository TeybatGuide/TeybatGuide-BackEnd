package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeaponSource {

    WISH("기원"),
    BATTLE_PASS("기행 보물함"),
    FORGED("단조"),
    EVENT("이벤트"),
    STORY_QUEST("전설임무"),
    WORLD_QUEST("월드임무"),
    FISHING_ASSOCIATION("낚시"),
    STARGLITTER("스타라이트"),
    MONDSTADT("몬드"),
    LIYUE("리월"),
    INAZUMA("이나즈마"),
    SUMERU("수메르"),
    FONTAINE("폰타인"),
    SNEZHNAYA("스네즈나야"),
    NATLAN("나타"),
    OTHER("-");


    private final String weaponSourceName;

}
