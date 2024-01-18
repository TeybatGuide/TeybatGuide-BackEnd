package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.*;

@Getter
@Entity
@Table(name = "characters")
public class Characters extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String characterName;

    @Column(length = 100)
    private String characterImage;

    @Enumerated(EnumType.STRING)
    private Stars stars;

    @Enumerated(EnumType.STRING)
    private Element element;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private WeaponType weaponType;

    protected Characters() {
        super(Domain.CHARACTER);
    }

    @Builder
    public Characters(String characterName, String characterImage, Stars stars, Element element, Country country, WeaponType weaponType) {
        this();
        this.characterName = characterName;
        this.characterImage = characterImage;
        this.stars = stars;
        this.element = element;
        this.country = country;
        this.weaponType = weaponType;
    }
}
