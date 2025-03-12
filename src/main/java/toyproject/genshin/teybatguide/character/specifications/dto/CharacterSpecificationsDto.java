package toyproject.genshin.teybatguide.character.specifications.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.character.specifications.entity.CharacterSpecifications;

public record CharacterSpecificationsDto(
        String health, String atk, String def, String critRate, String critDMG, String elementMastery
) {
    @Contract("_ -> new")
    public static @NotNull CharacterSpecificationsDto of(@NotNull CharacterSpecifications spec) {
        return new CharacterSpecificationsDto(
                spec.getSpecHealth(),
                spec.getSpecATK(),
                spec.getSpecDEF(),
                spec.getSpecCritRate(),
                spec.getSpecCritDMG(),
                spec.getSpecElementMastery()
        );
    }
}
