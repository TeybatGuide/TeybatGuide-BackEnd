package toyproject.genshin.teybatguide.controller.dto.artifact;

import toyproject.genshin.teybatguide.domain.value.ArtifactOptions;
import toyproject.genshin.teybatguide.domain.value.Country;

import java.util.List;

public record ArtifactListRequest(List<Country> countries, List<ArtifactOptions> artifactOptions) {
}
