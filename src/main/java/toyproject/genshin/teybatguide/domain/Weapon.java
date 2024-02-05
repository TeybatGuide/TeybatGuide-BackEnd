package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toyproject.genshin.teybatguide.domain.value.Domain;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponOptions;
import toyproject.genshin.teybatguide.domain.value.WeaponType;

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

    @Column(length = 30)
    private String weaponEffect;

    @Column(length = 500)
    private String weaponEffectExplanation;

    protected Weapon() {
        super(Domain.WEAPON);
    }

    @Builder
    public Weapon(String weaponName, String weaponImage, Stars stars, WeaponType weaponType, WeaponOptions weaponOption, String weaponEffect, String weaponEffectExplanation) {
        this();
        this.weaponName = weaponName;
        this.weaponImage = weaponImage;
        this.stars = stars;
        this.weaponType = weaponType;
        this.weaponOption = weaponOption;
        this.weaponEffect = weaponEffect;
        this.weaponEffectExplanation = weaponEffectExplanation;
    }
}
