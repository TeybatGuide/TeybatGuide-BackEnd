package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Event;

import java.time.LocalDateTime;

@Builder
public record BannerEventsResponse(String eventName, LocalDateTime startDate, LocalDateTime endDate) {

    public static BannerEventsResponse of(Event event) {
        return BannerEventsResponse.builder()
                .eventName(event.getEventName())
                .startDate(event.getEventStartDate())
                .endDate(event.getEventEndDate())
                .build();
    }

}
