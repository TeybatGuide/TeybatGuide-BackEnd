package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DayOfWeek {

    MON_THU_SUN("월요일/목요일/일요일"),
    TUE_FRI_SUN("화요일/금요일/일요일"),
    WED_SAT_SUN("수요일/토요일/일요일"),
    EVERYDAY("매일");

    private final String dayOfWeekName;
}
