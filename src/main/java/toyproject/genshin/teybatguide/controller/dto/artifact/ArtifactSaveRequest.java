package toyproject.genshin.teybatguide.controller.dto.artifact;

import toyproject.genshin.teybatguide.domain.value.ArtifactOptions;
import toyproject.genshin.teybatguide.domain.value.Country;

import java.util.Set;

public record ArtifactSaveRequest(String name, Country country, Set<ArtifactOptions> artifactOptions) {
}
