package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.value.Materials;

import java.util.List;

@Builder
public record MainResourcesResponse(String criterion, List<MainResourcesListDto> resources) {

    public static MainResourcesResponse of(Materials materials, List<MainResourcesListDto> resources) {
        return MainResourcesResponse.builder()
                .criterion(materials.getResourceByName())
                .resources(resources)
                .build();
    }

}
