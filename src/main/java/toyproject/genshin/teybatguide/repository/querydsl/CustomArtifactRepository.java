package toyproject.genshin.teybatguide.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListRequest;
import toyproject.genshin.teybatguide.domain.Artifact;

public interface CustomArtifactRepository {

    Page<Artifact> findByCountriesAndOptions(ArtifactListRequest request, Pageable pageable);

}
