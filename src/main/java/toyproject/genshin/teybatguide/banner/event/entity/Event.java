package toyproject.genshin.teybatguide.banner.event.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.base.BaseEntity;
import toyproject.genshin.teybatguide.banner.event.dto.BannerEventsDto;
import toyproject.genshin.teybatguide.base.value.Domain;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "event")
public class Event extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String eventName;

    @Column
    private LocalDateTime eventStartDate;

    @Column
    private LocalDateTime eventEndDate;

    protected Event() {
        super(Domain.EVENT);
    }

    @Builder
    public Event(String eventName, LocalDateTime eventStartDate, LocalDateTime eventEndDate) {
        this();
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public static Event of(BannerEventsDto request) {
        return Event.builder()
                .eventName(request.eventName())
                .eventStartDate(request.startDate())
                .eventEndDate(request.endDate())
                .build();
    }

}
