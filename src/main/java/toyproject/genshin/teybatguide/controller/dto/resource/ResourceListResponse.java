package toyproject.genshin.teybatguide.controller.dto.resource;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Resources;

@Builder
public record ResourceListResponse(String resourceId, String resourceName, DomainDto domain, String star, String imageUrls) {

    public static ResourceListResponse of(Resources resources) {
        return ResourceListResponse.builder()
                .resourceId(resources.getId())
                .resourceName(resources.getResourcesName())
                .domain(DomainDto.of(resources.getDomain()))
                .star(resources.getStars().getStarsName())
                .imageUrls(resources.getResourcesImage())
                .build();
    }

}
