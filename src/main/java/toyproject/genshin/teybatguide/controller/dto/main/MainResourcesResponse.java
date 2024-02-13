package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Resources;

@Builder
public record MainResourcesResponse(String id, String name, String imageUrls, String star) {

    public static MainResourcesResponse of(Resources resources) {
        return MainResourcesResponse.builder()
                .id(resources.getId())
                .name(resources.getResourcesName())
                .imageUrls(resources.getResourcesImage())
                .star(resources.getStars().getStarsName())
                .build();
    }

}
