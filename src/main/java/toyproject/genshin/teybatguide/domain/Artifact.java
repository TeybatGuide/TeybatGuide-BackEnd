package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.Domain;

@Entity
@Getter
@Table(name = "artifact")
public class Artifact extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String artifactName;

    @Column(nullable = false, length = 100)
    private String artifactImage;

    @Enumerated(EnumType.STRING)
    private Country country;

    protected Artifact() {
        super(Domain.ARTIFACT);
    }

    @Builder
    public Artifact(String artifactName, String artifactImage, Country country) {
        this();
        this.artifactName = artifactName;
        this.artifactImage = artifactImage;
        this.country = country;
    }
}
