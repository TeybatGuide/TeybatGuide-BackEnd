package toyproject.genshin.teybatguide.user.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.exception.TeybatBadRequestException;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.oauth.controller.dto.KakaoProfile;
import toyproject.genshin.teybatguide.user.controller.dto.UserInfoResponse;
import toyproject.genshin.teybatguide.user.entity.User;
import toyproject.genshin.teybatguide.user.repository.UserRepository;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUser(HttpServletRequest request) {
        User user = userRepository.findById(request.getHeader("id"))
            .orElseThrow(() -> new TeybatBadRequestException("아이디가 존재하지 않습니다."));

        return UserInfoResponse.of(user);
    }

    public Boolean existsUserByEmail(KakaoProfile profile) {
        return userRepository.existsByEmail(profile.getKakao_account().getEmail());
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new TeybatDataAccessException("저장에 실패하였습니다."));
    }

}
