package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListRequest;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListResponse;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactSaveRequest;
import toyproject.genshin.teybatguide.domain.Artifact;
import toyproject.genshin.teybatguide.domain.Domain;
import toyproject.genshin.teybatguide.exception.TeybatBadRequestException;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.exception.TeybatNotFoundException;
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

    /*
        todo
            CharacterListResponse 구현로직 추가
     */
    @Transactional
    public ArtifactListResponse saveArtifact(@NotNull ArtifactSaveRequest request) {
        Domain domain = domainRepository.findById(request.domain())
                .orElseThrow(() -> new TeybatBadRequestException("비경이 존재하지 않습니다."));

        Artifact entity = Artifact.of(request, domain);
        entity.setArtifactImage("/"+ entity.getId().replace("_", "/") + ".webp");
        artifactRepository.save(entity);

        Artifact artifact = artifactRepository.findById(entity.getId())
                .orElseThrow(() -> new TeybatDataAccessException("성유물 저장에 실패하였습니다."));

        return ArtifactListResponse.of(artifact);
    }

}
