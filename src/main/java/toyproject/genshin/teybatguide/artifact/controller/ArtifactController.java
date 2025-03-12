package toyproject.genshin.teybatguide.artifact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.artifact.service.ArtifactService;
import toyproject.genshin.teybatguide.base.ResponseData;
import toyproject.genshin.teybatguide.artifact.controller.dto.ArtifactListRequest;
import toyproject.genshin.teybatguide.artifact.controller.dto.ArtifactListResponse;
import toyproject.genshin.teybatguide.artifact.controller.dto.ArtifactSaveRequest;
import toyproject.genshin.teybatguide.base.dto.PageDto;
import toyproject.genshin.teybatguide.base.PageResponseData;

import java.util.List;

@RestController
@RequestMapping("/api/artifact")
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactService artifactService;

    @PostMapping
    public PageResponseData<List<ArtifactListResponse>> getArtifactList(
            @RequestBody ArtifactListRequest request,
            @PageableDefault(size = 15) Pageable pageable
    ) {
        Page<ArtifactListResponse> artifactListResponses = artifactService.searchArtifactList(request, pageable);
        return PageResponseData.of(artifactListResponses.stream().toList(), PageDto.of(artifactListResponses));
    }

    @PostMapping("/save")
    public ResponseData<ArtifactListResponse> saveArtifact(@RequestBody ArtifactSaveRequest request) {
        return ResponseData.of(artifactService.saveArtifact(request));
    }

}
