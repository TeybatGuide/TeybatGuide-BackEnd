package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponAscendSaveRequest;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.util.List;

@Entity
@Getter
@Table(name = "weapon_ascend")
public class WeaponAscend extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "id")
    private Weapon weapon;

    @ManyToOne
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    private Resources resources;

    @Column(nullable = false)
    private int weaponAscendCount;

    protected WeaponAscend() {
        super(Domain.WEAPON_ASCEND);
    }

    @Builder
    public WeaponAscend(Weapon weapon, Resources resources, int weaponAscendCount) {
        this();
        this.weapon = weapon;
        this.resources = resources;
        this.weaponAscendCount = weaponAscendCount;
    }

    public static WeaponAscend of(WeaponAscendSaveRequest request, Weapon weapon, Resources resources) {
        return WeaponAscend.builder()
                .weapon(weapon)
                .resources(resources)
                .weaponAscendCount(request.count())
                .build();
    }
}
