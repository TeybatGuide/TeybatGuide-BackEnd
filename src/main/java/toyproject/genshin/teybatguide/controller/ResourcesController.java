package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.controller.dto.ResourceSaveRequest;
import toyproject.genshin.teybatguide.service.ResourcesService;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    @PostMapping("/save")
    public String saveResources(@RequestBody ResourceSaveRequest request) {
        return resourcesService.saveResources(request);
    }

}
