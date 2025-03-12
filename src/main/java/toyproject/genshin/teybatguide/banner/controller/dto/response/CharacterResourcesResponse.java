package toyproject.genshin.teybatguide.banner.controller.dto.response;

import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.resource.controller.dto.ResourceListResponse;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.resource.entity.Resources;

import java.util.List;

public record CharacterResourcesResponse(String characterId, List<ResourceListResponse> responses) {

    public static @NotNull CharacterResourcesResponse of(
            @NotNull Characters characters, @NotNull List<Resources> resources
    ) {
        List<ResourceListResponse> responseList = resources.stream()
                .map(ResourceListResponse::of)
                .toList();
        return new CharacterResourcesResponse(characters.getId(), responseList);
    }

}
