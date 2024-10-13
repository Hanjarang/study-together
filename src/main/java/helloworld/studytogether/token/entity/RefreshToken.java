package helloworld.studytogether.token.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import helloworld.studytogether.user.entity.User; // User 엔티티 import

@Getter
@Setter
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "token_id")
  private Long tokenId;


  private String refresh;

  private String expiration; // 만료시간

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;


}