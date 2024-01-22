package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.util.List;

@Getter
@Entity
@Table(name = "character_talents")
public class CharacterTalents extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "charater_id", referencedColumnName = "id")
    private Characters characters;

    @OneToMany
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    private List<Resources> resources;

    @Column(nullable = false)
    private int characterTalentsCount;

    protected CharacterTalents() {
        super(Domain.CHARACTER_TALENTS);
    }

    @Builder
    public CharacterTalents(Characters characters, List<Resources> resources, int characterTalentsCount) {
        this();
        this.characters = characters;
        this.resources = resources;
        this.characterTalentsCount = characterTalentsCount;
    }
}
