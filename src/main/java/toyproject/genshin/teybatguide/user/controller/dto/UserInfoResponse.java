package toyproject.genshin.teybatguide.user.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.user.entity.User;

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
