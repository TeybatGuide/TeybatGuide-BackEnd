package toyproject.genshin.teybatguide.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.user.controller.dto.UserInfoResponse;
import toyproject.genshin.teybatguide.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getCurrentUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUser(request));
    }

}
