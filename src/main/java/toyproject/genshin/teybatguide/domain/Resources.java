package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.value.*;

import static toyproject.genshin.teybatguide.domain.value.Domain.*;

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
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Enumerated(EnumType.STRING)
    private Materials materials;

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "id")
    private Domain domain;

    protected Resources() {
        super(RESOURCES);
    }

    @Builder
    public Resources(String resourcesName, String resourcesImage, DayOfWeek dayOfWeek, Stars stars, Materials materials, Domain domain) {
        this();
        this.resourcesName = resourcesName;
        this.resourcesImage = resourcesImage;
        this.dayOfWeek = dayOfWeek;
        this.stars = stars;
        this.materials = materials;
        this.domain = domain;
    }

    public static Resources of(ResourceSaveRequest request, Domain domain) {
        return Resources.builder()
                .resourcesName(request.name())
                .resourcesImage("")
                .dayOfWeek(request.day())
                .stars(request.stars())
                .materials(request.materials())
                .domain(domain)
                .build();
    }
}
