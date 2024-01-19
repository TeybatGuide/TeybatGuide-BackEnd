package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Domain;

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

    protected Resources() {
        super(Domain.RESOURCES);
    }

    @Builder
    public Resources(String resourcesName, Country country, DayOfWeek dayOfWeek) {
        this();
        this.resourcesName = resourcesName;
        this.country = country;
        this.dayOfWeek = dayOfWeek;
    }
}
