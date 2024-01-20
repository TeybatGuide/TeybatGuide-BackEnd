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
    @JoinColumn(name = "id")
    private Characters characters;

    @Column(length = 10)
    private String specHealth;

    @Column(length = 10)
    private String specOffensive;

    @Column(length = 10)
    private String specDefensive;

    @Column(length = 10)
    private String specFetalHitProb;

    @Column(length = 10)
    private String specFetalDamage;

    @Column(length = 10)
    private String specElement;

    protected CharacterSpecifications() {
        super(Domain.CHARACTER_SPECIFICATIONS);
    }

    @Builder
    public CharacterSpecifications(Characters characters, String specHealth, String specOffensive, String specDefensive, String specFetalHitProb, String specFetalDamage, String specElement) {
        this();
        this.characters = characters;
        this.specHealth = specHealth;
        this.specOffensive = specOffensive;
        this.specDefensive = specDefensive;
        this.specFetalHitProb = specFetalHitProb;
        this.specFetalDamage = specFetalDamage;
        this.specElement = specElement;
    }
}
