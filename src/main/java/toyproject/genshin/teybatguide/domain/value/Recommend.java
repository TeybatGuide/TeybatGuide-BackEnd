package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Recommend {

    BEST("추천"), PRIORITY("우선도"), OTHER("기타");

    private final String recommendName;

}
