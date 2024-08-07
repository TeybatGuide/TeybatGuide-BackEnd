package toyproject.genshin.teybatguide.controller.dto.banner;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Event;

import java.time.LocalDateTime;

public record BannerEventsDto(String eventName, LocalDateTime startDate, LocalDateTime endDate) {

    @Contract("_ -> new")
    public static @NotNull BannerEventsDto of(@NotNull Event event) {
        return new BannerEventsDto(event.getEventName(), event.getEventStartDate(), event.getEventEndDate());
    }

}