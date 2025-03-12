package toyproject.genshin.teybatguide.character.entity.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Recommend {

    BEST("추천"), OTHER("기타");

    private final String recommendName;

}
