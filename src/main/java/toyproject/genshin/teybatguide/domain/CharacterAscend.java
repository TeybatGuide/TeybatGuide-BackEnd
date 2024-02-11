package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.util.List;

@Getter
@Entity
@Table(name = "character_ascend")
public class CharacterAscend extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    @ManyToOne
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    private Resources resources;

    @Column(nullable = false)
    private int characterAscendCount;

    protected CharacterAscend() {
        super(Domain.CHARACTER_ASCEND);
    }

    @Builder
    public CharacterAscend(Characters characters, int characterAscendCount) {
        this();
        this.characters = characters;
        this.characterAscendCount = characterAscendCount;
    }
}
