package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;
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
    private SignatureWeapon signatureWeapon;

    protected CharacterWeapon() {
        super(Domain.CHARACTER_WEAPON);
    }

    @Builder
    public CharacterWeapon(Characters characters, Weapon weapon, SignatureWeapon signatureWeapon) {
        this();
        this.characters = characters;
        this.weapon = weapon;
        this.signatureWeapon = signatureWeapon;
    }
}
