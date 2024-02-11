package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.controller.dto.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Domain;
import toyproject.genshin.teybatguide.domain.value.Stars;

import java.util.List;

@Entity
@Getter
@Table(name = "resources")
public class Resources extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String resourcesName;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    private Stars stars;

    protected Resources() {
        super(Domain.RESOURCES);
    }

    @Builder
    public Resources(String resourcesName, Country country, DayOfWeek dayOfWeek, Stars stars) {
        this();
        this.resourcesName = resourcesName;
        this.country = country;
        this.dayOfWeek = dayOfWeek;
        this.stars = stars;
    }

    public static Resources of(ResourceSaveRequest request) {
        return Resources.builder()
                .resourcesName(request.name())
                .country(request.country())
                .dayOfWeek(request.day())
                .stars(request.stars())
                .build();
    }
}
