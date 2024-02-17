package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListRequest;
import toyproject.genshin.teybatguide.domain.Artifact;

@RequiredArgsConstructor
public class CustomArtifactRepositoryImpl implements CustomArtifactRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Artifact> findByCountriesAndOptions(ArtifactListRequest request, Pageable pageable) {
        return null;
    }
}
