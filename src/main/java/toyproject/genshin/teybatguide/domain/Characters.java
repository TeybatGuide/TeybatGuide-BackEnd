package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.Element;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponType;

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



}
