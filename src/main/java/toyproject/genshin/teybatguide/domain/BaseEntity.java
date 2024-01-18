package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.io.Serializable;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 50)
    private String id;

    public BaseEntity() {
    }

    public BaseEntity(Domain domain) {
        id = String.join(domain.toString(), "_", UUID.randomUUID().toString().replace("-",""));
    }
}
