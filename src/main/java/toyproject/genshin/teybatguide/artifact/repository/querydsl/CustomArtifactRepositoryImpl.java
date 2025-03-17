package toyproject.genshin.teybatguide.artifact.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import toyproject.genshin.teybatguide.artifact.controller.dto.ArtifactListRequest;
import toyproject.genshin.teybatguide.artifact.entity.Artifact;
import toyproject.genshin.teybatguide.artifact.entity.value.ArtifactOptions;
import toyproject.genshin.teybatguide.base.value.Country;

import java.util.List;

import static toyproject.genshin.teybatguide.artifact.entity.QArtifact.artifact;
import static toyproject.genshin.teybatguide.domain.QDomain.domain;

@RequiredArgsConstructor
public class CustomArtifactRepositoryImpl implements CustomArtifactRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Artifact> findByCountriesAndOptions(ArtifactListRequest request, Pageable pageable) {
        List<Artifact> result = jpaQueryFactory
            .select(artifact)
            .from(artifact, domain)
            .where(
                artifact.domain.eq(domain),
                inCountries(request.countries()),
                inOptions(request.artifactOptions())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return PageableExecutionUtils.getPage(result, pageable, getCount(request)::fetchOne);
    }

    private JPAQuery<Long> getCount(ArtifactListRequest request) {
        return jpaQueryFactory
            .select(artifact.count())
            .from(artifact)
            .where(
                inCountries(request.countries()),
                inOptions(request.artifactOptions())
            );
    }

    private BooleanExpression inCountries(List<Country> countryList) {
        return countryList != null ? artifact.domain.country.in(countryList) : null;
    }

    private BooleanExpression inOptions(List<ArtifactOptions> options) {
        if (options != null && !options.isEmpty()) {
            return artifact.artifactOptions.isNotEmpty().and(artifact.artifactOptions.any().in(options));
        } else {
            return artifact.artifactOptions.isNotEmpty();
        }
    }
}
