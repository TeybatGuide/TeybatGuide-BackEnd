package toyproject.genshin.teybatguide.controller.dto.artifact;

import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.characters.response.CharacterListResponse;
import toyproject.genshin.teybatguide.domain.Artifact;
import toyproject.genshin.teybatguide.domain.value.ArtifactOptions;

import java.util.List;

public record ArtifactListResponse(
        String id, String artifactName, String imageUrls,
        List<String> artifactOptions, List<CharacterListResponse> characters
) {

    /*
        todo
            CharacterListResponse 로직 추가
     */

    public static @NotNull ArtifactListResponse of(@NotNull Artifact artifact) {
        List<String> options = artifact.getArtifactOptions().stream()
                .map(ArtifactOptions::getOptionsName)
                .toList();
        return new ArtifactListResponse(
                artifact.getId(), artifact.getArtifactName(), artifact.getArtifactImage(), options, null
        );
    }

}
