package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Options;

import java.util.List;

@Entity
@Getter
@Table(name = "artifact_options")
public class ArtifactOptions extends BaseEntity {

    @OneToMany
    private List<Artifact> artifacts;

    @Enumerated(EnumType.STRING)
    private Options options;

}
