package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.repository.querydsl.CustomCharacterBannerRepository;

@GraphQlRepository
public interface CharacterBannerRepository extends JpaRepository<CharacterBanner, String>, CustomCharacterBannerRepository {
}
