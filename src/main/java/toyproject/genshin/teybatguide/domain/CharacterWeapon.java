package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.CharacterWeaponOnly;
import toyproject.genshin.teybatguide.domain.value.Domain;

@Getter
@Entity
@Table(name = "character_weapon")
public class CharacterWeapon extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    @ManyToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "id")
    private Weapon weapon;

    @Enumerated(EnumType.STRING)
    private CharacterWeaponOnly characterWeaponOnly;

    protected CharacterWeapon() {
        super(Domain.CHARACTER_WEAPON);
    }

    @Builder
    public CharacterWeapon(Characters characters, Weapon weapon, CharacterWeaponOnly characterWeaponOnly) {
        this();
        this.characters = characters;
        this.weapon = weapon;
        this.characterWeaponOnly = characterWeaponOnly;
    }
}
