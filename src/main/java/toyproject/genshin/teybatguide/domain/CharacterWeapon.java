package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.controller.dto.CharacterWeaponSaveRequest;
import toyproject.genshin.teybatguide.domain.value.Recommend;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;
import toyproject.genshin.teybatguide.domain.value.Domain;
import toyproject.genshin.teybatguide.domain.value.WeaponCriteria;

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

    @Enumerated(EnumType.STRING)
    private WeaponCriteria weaponCriteria;

    @Enumerated(EnumType.STRING)
    private Recommend recommend;

    @Column(length = 100)
    private String comments;

    @Column(length = 10)
    private String version;

    protected CharacterWeapon() {
        super(Domain.CHARACTER_WEAPON);
    }

    @Builder
    public CharacterWeapon(Characters characters, Weapon weapon, SignatureWeapon signatureWeapon, WeaponCriteria weaponCriteria, Recommend recommend, String comments, String version) {
        this();
        this.characters = characters;
        this.weapon = weapon;
        this.signatureWeapon = signatureWeapon;
        this.weaponCriteria = weaponCriteria;
        this.recommend = recommend;
        this.comments = comments;
        this.version = version;
    }

    public static CharacterWeapon of(Characters characters, Weapon weapon, CharacterWeaponSaveRequest request) {
        return CharacterWeapon.builder()
                .characters(characters)
                .weapon(weapon)
                .signatureWeapon(request.signatureWeapon())
                .weaponCriteria(request.criteria())
                .recommend(request.recommend())
                .comments(request.text())
                .version(request.version())
                .build();
    }
}
