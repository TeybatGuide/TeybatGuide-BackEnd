package toyproject.genshin.teybatguide.controller.dto.resource;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Resources;

public record ResourceListResponse(
        String resourceId, String resourceName, DomainDto domain, String star, String imageUrls
) {
    @Contract("_ -> new")
    public static @NotNull ResourceListResponse of(@NotNull Resources resources) {
        return new ResourceListResponse(
                resources.getId(),
                resources.getResourcesName(),
                DomainDto.of(resources.getDomain()),
                resources.getStars().getStarsName(),
                resources.getResourcesImage()
        );
    }

}
