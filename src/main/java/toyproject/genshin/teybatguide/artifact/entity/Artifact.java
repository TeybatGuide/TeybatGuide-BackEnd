package toyproject.genshin.teybatguide.artifact.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toyproject.genshin.teybatguide.base.BaseEntity;
import toyproject.genshin.teybatguide.artifact.controller.dto.ArtifactSaveRequest;
import toyproject.genshin.teybatguide.artifact.entity.value.ArtifactOptions;
import toyproject.genshin.teybatguide.domain.Domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static toyproject.genshin.teybatguide.base.value.Domain.ARTIFACT;

@Entity
@Getter
@Table(name = "artifact")
public class Artifact extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String artifactName;

    @Setter
    @Column(nullable = false, length = 100)
    private String artifactImage;

    @ElementCollection(targetClass = ArtifactOptions.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "artifact_options", joinColumns = @JoinColumn(name = "artifact_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "artifact_options_name")
    private Set<ArtifactOptions> artifactOptions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "id")
    private Domain domain;

    @OneToMany(mappedBy = "artifact")
    private List<CharacterArtifact> characterArtifacts;

    protected Artifact() {
        super(ARTIFACT);
    }

    @Builder
    public Artifact(String artifactName, String artifactImage, toyproject.genshin.teybatguide.domain.Domain domain, Set<ArtifactOptions> artifactOptions) {
        this();
        this.artifactName = artifactName;
        this.artifactImage = artifactImage;
        this.domain = domain;
        this.artifactOptions = artifactOptions;
    }

    public static Artifact of(ArtifactSaveRequest request, Domain domain) {
        return Artifact.builder()
                .artifactName(request.name())
                .artifactImage("-")
                .artifactOptions(request.artifactOptions())
                .domain(domain)
                .build();
    }
}
