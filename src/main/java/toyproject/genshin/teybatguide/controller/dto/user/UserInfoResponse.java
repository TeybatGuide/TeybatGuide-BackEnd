package toyproject.genshin.teybatguide.controller.dto.user;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.User;

@Builder
public record UserInfoResponse(String id, String nickname, String email, String profileImage) {

    public static UserInfoResponse of(User user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .build();
    }

}
