package toyproject.genshin.teybatguide.controller.dto.characters;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterSpecifications;

@Builder
public record CharacterSpecificationsDto(String health, String atk, String def, String critRate, String critDMG, String elementMastery) {

    public static CharacterSpecificationsDto of(CharacterSpecifications specifications) {
        return CharacterSpecificationsDto.builder()
                .health(specifications.getSpecHealth())
                .atk(specifications.getSpecATK())
                .def(specifications.getSpecDEF())
                .critRate(specifications.getSpecCritRate())
                .critDMG(specifications.getSpecCritDMG())
                .elementMastery(specifications.getSpecElementMastery())
                .build();
    }


}
