package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Resources;

import java.util.List;

@Builder
public record MainCharacterResourcesResponse(String characterId, List<ResourceListResponse> responses) {

    public static MainCharacterResourcesResponse of(Characters characters, List<Resources> resources) {
        return MainCharacterResourcesResponse.builder()
                .characterId(characters.getId())
                .responses(
                        resources.stream()
                        .map(ResourceListResponse::of)
                        .toList()
                )
                .build();
    }

}
