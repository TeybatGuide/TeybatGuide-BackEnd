package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import toyproject.genshin.teybatguide.controller.dto.artifact.ArtifactListRequest;
import toyproject.genshin.teybatguide.domain.Artifact;
import toyproject.genshin.teybatguide.domain.value.ArtifactOptions;
import toyproject.genshin.teybatguide.domain.value.Country;

import java.util.List;

import static toyproject.genshin.teybatguide.domain.QArtifact.artifact;

@RequiredArgsConstructor
public class CustomArtifactRepositoryImpl implements CustomArtifactRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Artifact> findByCountriesAndOptions(ArtifactListRequest request, Pageable pageable) {
        List<Artifact> result = jpaQueryFactory
                .selectFrom(artifact)
                .where(
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
        return countryList != null ? artifact.country.in(countryList) : artifact.country.in(Country.values());
    }

    private BooleanExpression inOptions(List<ArtifactOptions> options) {
        if (options != null && !options.isEmpty()) {
            return artifact.artifactOptions.isNotEmpty().and(artifact.artifactOptions.any().in(options));
        } else {
            return artifact.artifactOptions.isNotEmpty();
        }
    }
}
