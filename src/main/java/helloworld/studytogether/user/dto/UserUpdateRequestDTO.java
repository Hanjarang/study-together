package helloworld.studytogether.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDTO {

    @NotBlank
    @Size(min = 2, max = 30)
    private String nickname;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6) // 최소 길이를 설정
    private String password; // 패스워드 필드 추가

}
