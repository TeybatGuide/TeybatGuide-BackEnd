package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.base.PageDto;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListRequest;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
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

    @PostMapping("/save")
    public String saveResources(@RequestBody ResourceSaveRequest request) {
        return resourcesService.saveResources(request);
    }

    @QueryMapping
    public PageResponseData<List<ResourceListResponse>> getResources(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam DayOfWeek dayOfWeek
    ) {
        Page<ResourceListResponse> responses = resourcesService.searchResourcesByDayOfWeek(dayOfWeek, pageable);
        return PageResponseData.of(responses.stream().toList(), PageDto.of(responses));
    }

}
