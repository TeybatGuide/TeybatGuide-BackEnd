package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.controller.dto.base.PageDto;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListRequest;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListResponse;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceSaveRequest;
import toyproject.genshin.teybatguide.service.ResourcesService;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    @PostMapping
    public PageResponseData<List<ResourceListResponse>> getResourceList(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestBody ResourceListRequest request
            ) {
        Page<ResourceListResponse> responses = resourcesService.searchResourceList(request, pageable);
        return PageResponseData.of(responses.stream().toList(), PageDto.of(responses));
    }

    @PostMapping("/save")
    public String saveResources(@RequestBody ResourceSaveRequest request) {
        return resourcesService.saveResources(request);
    }

}
