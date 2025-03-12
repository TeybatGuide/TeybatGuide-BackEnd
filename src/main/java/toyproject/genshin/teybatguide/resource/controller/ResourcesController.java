package toyproject.genshin.teybatguide.resource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.base.ResponseData;
import toyproject.genshin.teybatguide.base.dto.PageDto;
import toyproject.genshin.teybatguide.base.PageResponseData;
import toyproject.genshin.teybatguide.banner.controller.dto.response.ResourcesResponse;
import toyproject.genshin.teybatguide.resource.service.ResourcesService;
import toyproject.genshin.teybatguide.resource.controller.dto.ResourceListRequest;
import toyproject.genshin.teybatguide.resource.controller.dto.ResourceListResponse;
import toyproject.genshin.teybatguide.resource.controller.dto.ResourceSaveRequest;
import toyproject.genshin.teybatguide.resource.entity.value.Materials;
import toyproject.genshin.teybatguide.base.value.SortDirection;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    @GetMapping
    public PageResponseData<List<ResourceListResponse>> getResourceList(
            @PageableDefault(size = 20) Pageable pageable,
            @ModelAttribute ResourceListRequest request
    ) {
        Page<ResourceListResponse> responses = resourcesService.searchResourceList(request, pageable);
        return PageResponseData.of(responses.stream().toList(), PageDto.of(responses));
    }

    @QueryMapping
    public ResourcesResponse getResourcesToday(
            @Argument String materials,
            @Argument int limit,
            @Argument int offset,
            @Argument String sortAttribute,
            @Argument String sortDirection
    ) {
        Sort sort = Sort.by(sortAttribute);
        sort = isSortDirectionAscending(sortDirection) ? sort.ascending() : sort.descending();

        return resourcesService.searchResourcesForMaterials(Materials.of(materials), PageRequest.of(offset, limit, sort)).wrapper();
    }

    @PostMapping("/save")
    public ResponseData<ResourceListResponse> saveResources(@RequestBody ResourceSaveRequest request) {
        return ResponseData.of(resourcesService.saveResources(request));
    }

    private boolean isSortDirectionAscending(String sortDirection) {
        return sortDirection.toLowerCase().equals(SortDirection.ASCENDING.toString());
    }

}
