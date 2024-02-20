package toyproject.genshin.teybatguide.controller.dto.artifact;

import lombok.Builder;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.domain.Artifact;
import toyproject.genshin.teybatguide.domain.value.ArtifactOptions;

import java.util.List;

@Builder
public record ArtifactListResponse(String id, String artifactName, String imageUrls, List<String> artifactOptions, List<CharacterListResponse> characters) {

    public static ArtifactListResponse of(Artifact artifact) {
        List<String> options = artifact.getArtifactOptions().stream()
                .map(ArtifactOptions::getOptionsName)
                .toList();

        return ArtifactListResponse.builder()
                .id(artifact.getId())
                .artifactName(artifact.getArtifactName())
                .imageUrls(artifact.getArtifactImage())
                .artifactOptions(options)
                .build();
    }

}
