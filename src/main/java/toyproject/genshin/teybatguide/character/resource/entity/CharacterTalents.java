package toyproject.genshin.teybatguide.character.resource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.base.value.Domain;
import toyproject.genshin.teybatguide.character.entity.Characters;

@Getter
@Entity
@Table(name = "character_talents")
public class CharacterTalents extends CharacterResource {

    protected CharacterTalents() {
        super(Domain.CHARACTER_TALENTS);
    }

    @Builder
    public CharacterTalents(Characters characters, int characterTalentsCount) {
        super(Domain.CHARACTER_TALENTS, characters, characterTalentsCount);
    }
}
