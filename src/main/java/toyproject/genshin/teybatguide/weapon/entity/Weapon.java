package toyproject.genshin.teybatguide.weapon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toyproject.genshin.teybatguide.base.BaseEntity;
import toyproject.genshin.teybatguide.base.value.Stars;
import toyproject.genshin.teybatguide.base.value.Domain;
import toyproject.genshin.teybatguide.character.characterWeapon.entity.CharacterWeapon;
import toyproject.genshin.teybatguide.weapon.ascend.entity.WeaponAscend;
import toyproject.genshin.teybatguide.weapon.entity.value.WeaponOptions;
import toyproject.genshin.teybatguide.weapon.entity.value.WeaponSource;
import toyproject.genshin.teybatguide.weapon.entity.value.WeaponType;

import java.util.List;

@Entity
@Getter
@Table(name = "weapon")
public class Weapon extends BaseEntity {

    @Column(length = 50)
    private String weaponName;

    @Setter
    @Column(length = 100)
    private String weaponImage;

    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Enumerated(EnumType.STRING)
    private WeaponType weaponType;

    @Enumerated(EnumType.STRING)
    private WeaponOptions weaponOption;

    @Enumerated(EnumType.STRING)
    private WeaponSource weaponSource;

    @Column(length = 100)
    private String weaponSourceExplanation;

    @Column(length = 30)
    private String weaponEffect;

    @Column(length = 500)
    private String weaponEffectExplanation;

    @OneToMany(mappedBy = "weapon")
    private List<CharacterWeapon> characterWeapons;

    @OneToMany(mappedBy = "weapon")
    private List<WeaponAscend> weaponAscends;

    protected Weapon() {
        super(Domain.WEAPON);
    }

    @Builder
    public Weapon(String weaponName, String weaponImage, Stars stars, WeaponType weaponType, WeaponOptions weaponOption, WeaponSource weaponSource, String weaponSourceExplanation, String weaponEffect, String weaponEffectExplanation) {
        this();
        this.weaponName = weaponName;
        this.weaponImage = weaponImage;
        this.stars = stars;
        this.weaponType = weaponType;
        this.weaponOption = weaponOption;
        this.weaponSource = weaponSource;
        this.weaponSourceExplanation = weaponSourceExplanation;
        this.weaponEffect = weaponEffect;
        this.weaponEffectExplanation = weaponEffectExplanation;
    }
}
