package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.base.dto.PageDto;
import toyproject.genshin.teybatguide.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.main.MainResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListRequest;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.value.Materials;
import toyproject.genshin.teybatguide.domain.value.SortDirection;
import toyproject.genshin.teybatguide.service.ResourcesService;

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
    public MainResourcesResponse getResourcesToday(
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
    public ResponseEntity<ResourceListResponse> saveResources(@RequestBody ResourceSaveRequest request) {
        return ResponseEntity.ok().body(resourcesService.saveResources(request));
    }

    private boolean isSortDirectionAscending(String sortDirection) {
        return sortDirection.toLowerCase().equals(SortDirection.ASCENDING.toString());
    }

}
