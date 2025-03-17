package toyproject.genshin.teybatguide.character.resource.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.base.BaseEntity;
import toyproject.genshin.teybatguide.base.value.Domain;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.resource.entity.Resources;

@Getter
@MappedSuperclass
public abstract class CharacterResource extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    @ManyToOne
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    private Resources resources;

    @Column(nullable = false)
    private int resourceCount;

    protected CharacterResource(Domain domain) {
        super(domain);
    }

    public CharacterResource(Domain domain, Characters characters, int resourceCount) {
        this(domain);
        this.characters = characters;
        this.resourceCount = resourceCount;
    }
}

