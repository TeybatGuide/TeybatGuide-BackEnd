package toyproject.genshin.teybatguide.character.resource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.base.value.Domain;
import toyproject.genshin.teybatguide.character.entity.Characters;

@Getter
@Entity
@Table(name = "character_ascend")
public class CharacterAscend extends CharacterResource {

    protected CharacterAscend() {
        super(Domain.CHARACTER_ASCEND);
    }

    @Builder
    public CharacterAscend(Characters characters, int characterAscendCount) {
        super(Domain.CHARACTER_ASCEND, characters, characterAscendCount);
    }
}
