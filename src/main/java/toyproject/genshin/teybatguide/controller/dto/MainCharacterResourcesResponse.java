package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;

import java.util.List;

@Builder
public record MainCharacterResourcesResponse(String characterId, List<ResourceListResponse> responses) {
}
