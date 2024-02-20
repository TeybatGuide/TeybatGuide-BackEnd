package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListRequest;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListResponse;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactSaveRequest;
import toyproject.genshin.teybatguide.domain.Artifact;
import toyproject.genshin.teybatguide.domain.Domain;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.ArtifactRepository;
import toyproject.genshin.teybatguide.repository.DomainRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtifactService {

    private final ArtifactRepository artifactRepository;
    private final DomainRepository domainRepository;

    public Page<ArtifactListResponse> searchArtifactList(ArtifactListRequest request, Pageable pageable) {
        return artifactRepository.findByCountriesAndOptions(request, pageable)
                .map(ArtifactListResponse::of);
    }

    @Transactional
    public String saveArtifact(ArtifactSaveRequest request) {
        Domain domain = domainRepository.findById(request.domain())
                .orElseThrow(() -> new TeybatException("비경 id가 존재하지 않습니다."));
        Artifact entity = Artifact.of(request, domain);
        entity.setArtifactImage("/"+ entity.getId().replace("_", "/") + ".webp");
        artifactRepository.save(entity);
        return "good";
    }

}
