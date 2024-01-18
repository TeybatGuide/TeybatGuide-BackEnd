package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Element {

    ANEMO("바람"),
    GEO("바위"),
    ELECTRO("전기"),
    DENDRO("풀"),
    HYDRO("물"),
    PYRO("불"),
    CRYO("얼음");

    private final String elementName;

}
