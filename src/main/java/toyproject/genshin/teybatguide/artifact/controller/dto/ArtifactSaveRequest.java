package toyproject.genshin.teybatguide.artifact.controller.dto;

import toyproject.genshin.teybatguide.artifact.entity.value.ArtifactOptions;

import java.util.Set;

public record ArtifactSaveRequest(String name, String domain, Set<ArtifactOptions> artifactOptions) {
}
