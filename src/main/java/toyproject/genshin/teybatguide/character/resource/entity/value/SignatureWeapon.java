package toyproject.genshin.teybatguide.character.resource.entity.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignatureWeapon {

    TRUE("전용 무기"), FALSE("");

    private final String signatureWeaponName;

}
