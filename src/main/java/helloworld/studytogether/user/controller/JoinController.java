package helloworld.studytogether.user.controller;

import helloworld.studytogether.user.dto.JoinDTO;
import helloworld.studytogether.user.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class JoinController {

  private final JoinService joinService;

  public JoinController(JoinService joinService) {

    this.joinService = joinService;

  }


  @PostMapping("/users/join")
  public String joinProcess(@RequestBody JoinDTO joinDTO) {
    System.out.println("data =" + joinDTO);
    joinService.joinProcess(joinDTO);
    return "회원가입이 완료되었습니다. 이제 로그인해 주세요.";
  }
}
