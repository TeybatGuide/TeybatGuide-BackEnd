package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.value.*;
import toyproject.genshin.teybatguide.domain.value.Domain;

@Entity
@Getter
@Table(name = "resources")
public class Resources extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String resourcesName;

    @Setter
    @Column(nullable = false, length = 100)
    private String resourcesImage;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Enumerated(EnumType.STRING)
    private Materials materials;

    protected Resources() {
        super(Domain.RESOURCES);
    }

    @Builder
    public Resources(String resourcesName, String resourcesImage, Country country, DayOfWeek dayOfWeek, Stars stars, Materials materials) {
        this();
        this.resourcesName = resourcesName;
        this.resourcesImage = resourcesImage;
        this.country = country;
        this.dayOfWeek = dayOfWeek;
        this.stars = stars;
        this.materials = materials;
    }

    public static Resources of(ResourceSaveRequest request) {
        return Resources.builder()
                .resourcesName(request.name())
                .resourcesImage("")
                .country(request.country())
                .dayOfWeek(request.day())
                .stars(request.stars())
                .materials(request.materials())
                .build();
    }
}
