package toyproject.genshin.teybatguide.controller.dto.banner.response;

import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Resources;

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
