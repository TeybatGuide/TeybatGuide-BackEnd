package toyproject.genshin.teybatguide.artifact.controller.dto;

import toyproject.genshin.teybatguide.artifact.entity.value.ArtifactOptions;
import toyproject.genshin.teybatguide.base.value.Country;

import java.util.List;

public record ArtifactListRequest(List<Country> countries, List<ArtifactOptions> artifactOptions) {
}
