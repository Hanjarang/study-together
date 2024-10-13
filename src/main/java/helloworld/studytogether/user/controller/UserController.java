package helloworld.studytogether.user.controller;

import helloworld.studytogether.user.dto.UserInfoDTO;
import helloworld.studytogether.user.dto.UserUpdateRequestDTO;
import helloworld.studytogether.user.entity.User;
import helloworld.studytogether.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원 정보 조회 엔드포인트
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable Long userId) {
        UserInfoDTO userInfo = userService.getUserInfoById(userId);
        return ResponseEntity.ok(userInfo);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDTO updateRequest) {
        userService.updateUserById(userId, updateRequest);
        return ResponseEntity.ok("회원정보가 수정되었습니다.");
    }

}
