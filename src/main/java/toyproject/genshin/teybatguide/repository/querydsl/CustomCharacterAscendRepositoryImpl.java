package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.value.Materials;
import toyproject.genshin.teybatguide.domain.value.Stars;

import java.util.List;
import java.util.Map;

import static com.querydsl.core.group.GroupBy.list;
import static toyproject.genshin.teybatguide.domain.QCharacterAscend.characterAscend;
import static toyproject.genshin.teybatguide.domain.QResources.resources;

@RequiredArgsConstructor
public class CustomCharacterAscendRepositoryImpl implements CustomCharacterAscendRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Map<Characters, List<Resources>> findByCharacters(List<Characters> characters) {
        return jpaQueryFactory
                .from(characterAscend)
                .where(
                        inCharacters(characters),
                        eqStarsAndMaterials()
                )
                .transform(GroupBy
                        .groupBy(characterAscend.characters)
                        .as(list(characterAscend.resources))
                );

    }

    private BooleanExpression inCharacters(List<Characters> characters) {
        return characterAscend.characters.in(characters);
    }

    private BooleanExpression eqStarsAndMaterials() {
        BooleanExpression characterEnhancement = eqMaterials(Materials.CHARACTERS_ENHANCEMENT_MATERIAL);
        BooleanExpression characterTalent = eqMaterials(Materials.CHARACTERS_TALENT_MATERIAL);
        BooleanExpression characterWeaponEnhancement = eqMaterials(Materials.CHARACTERS_WEAPONS_ENHANCEMENT_MATERIAL)
                .and(eqMaterialsDetailsOther())
                .and(eqStar(Stars.THREE));

        return Expressions.anyOf(characterTalent, characterWeaponEnhancement, characterEnhancement);
    }

    private BooleanExpression eqMaterials(Materials materials) {
        return resources.materials.eq(materials);
    }

    private BooleanExpression eqMaterialsDetailsOther() {
        return resources.materialsDetails.eq(Materials.OTHER);
    }

    private BooleanExpression eqStar(Stars stars) {
        return resources.stars.eq(stars);
    }
}
