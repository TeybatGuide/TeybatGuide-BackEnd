package toyproject.genshin.teybatguide.artifact.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.artifact.controller.dto.ArtifactListRequest;
import toyproject.genshin.teybatguide.artifact.entity.Artifact;

public interface CustomArtifactRepository {

    Page<Artifact> findByCountriesAndOptions(ArtifactListRequest request, Pageable pageable);

}
