package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import toyproject.genshin.teybatguide.controller.dto.oauth.KakaoProfile;
import toyproject.genshin.teybatguide.domain.value.Domain;
import toyproject.genshin.teybatguide.domain.value.Role;

import java.sql.Timestamp;


@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private Long socialId;

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
    public User(Long socialId, String profileImage, String nickname, String email, Role userRole) {
        this();
        this.socialId = socialId;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.email = email;
        this.userRole = userRole;
    }

    public static User of(KakaoProfile profile) {
        return User.builder()
                .socialId(profile.getId())
                .profileImage(profile.getKakao_account().getProfile().getProfile_image_url())
                .nickname(profile.getKakao_account().getProfile().getNickname())
                .email(profile.getKakao_account().getEmail())
                .userRole(Role.USER)
                .build();
    }
}
