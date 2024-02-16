package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Event;

import java.time.LocalDateTime;

@Builder
public record BannerEventsDto(String eventName, LocalDateTime startDate, LocalDateTime endDate) {

    public static BannerEventsDto of(Event event) {
        return BannerEventsDto.builder()
                .eventName(event.getEventName())
                .startDate(event.getEventStartDate())
                .endDate(event.getEventEndDate())
                .build();
    }

}
