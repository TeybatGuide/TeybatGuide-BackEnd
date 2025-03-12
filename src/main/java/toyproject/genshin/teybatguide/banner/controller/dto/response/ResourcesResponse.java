package toyproject.genshin.teybatguide.banner.controller.dto.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.resource.controller.dto.ResourceListResponse;
import toyproject.genshin.teybatguide.resource.entity.value.Materials;

import java.util.List;

public record ResourcesResponse(String criterion, List<ResourceListResponse> resources) {

    @Contract("_, _ -> new")
    public static @NotNull ResourcesResponse of(
            @NotNull Materials materials, List<ResourceListResponse> resources
    ) {
        return new ResourcesResponse(materials.getResourceByName(), resources);
    }

}
