package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListRequest;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListResponse;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactSaveRequest;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.service.ArtifactService;

import java.util.List;

@RestController
@RequestMapping("/api/artifact")
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactService artifactService;

    @PostMapping
    public PageResponseData<List<ArtifactListResponse>> getArtifactList(
            @RequestBody ArtifactListRequest request,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return null;
    }

    @PostMapping("/save")
    public String saveArtifact(@RequestBody ArtifactSaveRequest request) {
        return artifactService.saveArtifact(request);
    }

}
