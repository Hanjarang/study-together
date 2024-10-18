package helloworld.studytogether.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;
    private String nickname;
    private Date created_at;


    private CountInfo count;  // count라는 객체에 등록 글/답변/채택답변 개수를 포함

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CountInfo {
        private int questionCount; // 등록한 문제 개수
        private int answerCount; // 등록한 답변 개수
        private int selectedAnswerCount; // 채택된 답변 개수
    }

}
