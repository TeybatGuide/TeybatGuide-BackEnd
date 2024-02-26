package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import toyproject.genshin.teybatguide.domain.value.Domain;
import toyproject.genshin.teybatguide.domain.value.Role;

import java.sql.Timestamp;


@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String loginId;

    @Column(length = 100)
    private String profileImage;

    @Column(length = 100)
    private String nickname;

    @Column(length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @CreatedDate
    private Timestamp createdDate;

    @LastModifiedDate
    private Timestamp modifiedDate;

    protected User() {
        super(Domain.USER);
    }

    @Builder
    public User(String loginId, String profileImage, String nickname, String email, Role userRole) {
        this();
        this.loginId = loginId;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.email = email;
        this.userRole = userRole;
    }
}
