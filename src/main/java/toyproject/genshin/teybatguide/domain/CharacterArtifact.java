package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

@Getter
@Entity
@Table(name = "character_artifact")
public class CharacterArtifact extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    @ManyToOne
    @JoinColumn(name = "artifact_id", referencedColumnName = "id")
    private Artifact artifact;

    @Column(length = 80)
    private String characterArtifactExplanation;

    protected CharacterArtifact() {
        super(Domain.CHARACTER_ARTIFACT);
    }

    @Builder
    public CharacterArtifact(Characters characters, Artifact artifact, String characterArtifactExplanation) {
        this();
        this.characters = characters;
        this.artifact = artifact;
        this.characterArtifactExplanation = characterArtifactExplanation;
    }
}
