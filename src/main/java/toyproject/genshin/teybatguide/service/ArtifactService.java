package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactSaveRequest;
import toyproject.genshin.teybatguide.domain.Artifact;
import toyproject.genshin.teybatguide.repository.ArtifactRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    @Transactional
    public String saveArtifact(ArtifactSaveRequest request) {
        Artifact entity = Artifact.of(request);
        entity.setArtifactImage("/"+ entity.getId().replace("_", "/") + ".webp");
        artifactRepository.save(entity);
        return "good";
    }

}
