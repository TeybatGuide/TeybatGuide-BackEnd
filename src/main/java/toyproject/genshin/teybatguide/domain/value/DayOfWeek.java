package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.exception.TeybatException;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum DayOfWeek {

    MON_THU_SUN(
            "월요일/목요일/일요일",
            List.of(
                    java.time.DayOfWeek.MONDAY,
                    java.time.DayOfWeek.THURSDAY,
                    java.time.DayOfWeek.SUNDAY)
    ),
    TUE_FRI_SUN(
            "화요일/금요일/일요일",
            List.of(java.time.DayOfWeek.TUESDAY,
                    java.time.DayOfWeek.FRIDAY,
                    java.time.DayOfWeek.SUNDAY)
    ),
    WED_SAT_SUN(
            "수요일/토요일/일요일",
            List.of(
                    java.time.DayOfWeek.WEDNESDAY,
                    java.time.DayOfWeek.SATURDAY,
                    java.time.DayOfWeek.SUNDAY)
    ),
    EVERYDAY("매일", List.of(java.time.DayOfWeek.values()));

    private final String dayOfWeekName;
    private final List<java.time.DayOfWeek> dayOfWeeks;

    public static DayOfWeek of(java.time.DayOfWeek dayOfWeek) {
        return Arrays.stream(DayOfWeek.values())
                .filter(day -> day.isTodaysDayOfWeek(dayOfWeek))
                .findAny()
                .orElseThrow(() -> new TeybatException("일치하는 날짜가 없습니다."));
    }

    private boolean isTodaysDayOfWeek(java.time.DayOfWeek dayOfWeek) {
        return this.dayOfWeeks.contains(dayOfWeek) && !this.equals(DayOfWeek.EVERYDAY);
    }
}
