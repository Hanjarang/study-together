package helloworld.studytogether.user.service;

import helloworld.studytogether.user.dto.UserInfoDTO;
import helloworld.studytogether.user.dto.UserUpdateRequestDTO;
import helloworld.studytogether.user.entity.User;
import helloworld.studytogether.user.repository.UserRepository;
import helloworld.studytogether.jwt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }


    // userId로 사용자 정보를 조회
    public UserInfoDTO getUserInfoById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자의 아이디를 찾을 수 없습니다.: " + userId));

        // 필요한 정보만 DTO로 변환
        return new UserInfoDTO(user.getUsername(), user.getEmail(), user.getNickname(),user.getCreated_at());
    }

    // userId로 사용자 정보를 수정

    public void updateUserById(Long userId, UserUpdateRequestDTO updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId));

        // 수정 가능한 필드 업데이트
        user.setNickname(updateRequest.getNickname());
        user.setEmail(updateRequest.getEmail());
        user.setPassword(updateRequest.getPassword()); // 패스워드 업데이트

        // 저장
        userRepository.save(user);
    }


}
