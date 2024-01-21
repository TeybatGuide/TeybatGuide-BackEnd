package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.Domain;
import toyproject.genshin.teybatguide.domain.value.Options;

import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "artifact_options",
            joinColumns = @JoinColumn(name = "artifact_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "artifact_options_name")
    private Set<Options> artifactOptions = new HashSet<>();

    protected Artifact() {
        super(Domain.ARTIFACT);
    }

    @Builder
    public Artifact(String artifactName, String artifactImage, Country country, Set<Options> artifactOptions) {
        this();
        this.artifactName = artifactName;
        this.artifactImage = artifactImage;
        this.country = country;
        this.artifactOptions = artifactOptions;
    }
}
