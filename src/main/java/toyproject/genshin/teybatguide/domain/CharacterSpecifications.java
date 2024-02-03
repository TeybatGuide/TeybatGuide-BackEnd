package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

@Entity
@Getter
@Table(name = "character_specifications")
public class CharacterSpecifications extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    @Column(length = 10)
    private String specHealth;

    @Column(length = 10)
    private String specATK;

    @Column(length = 10)
    private String specDEF;

    @Column(length = 10)
    private String specCritRate;

    @Column(length = 10)
    private String specCritDMG;

    @Column(length = 10)
    private String specElementMastery;

    protected CharacterSpecifications() {
        super(Domain.CHARACTER_SPECIFICATIONS);
    }

    @Builder
    public CharacterSpecifications(Characters characters, String specHealth, String specATK, String specDEF, String specCritRate, String specCritDMG, String specElementMastery) {
        this();
        this.characters = characters;
        this.specHealth = specHealth;
        this.specATK = specATK;
        this.specDEF = specDEF;
        this.specCritRate = specCritRate;
        this.specCritDMG = specCritDMG;
        this.specElementMastery = specElementMastery;
    }
}
