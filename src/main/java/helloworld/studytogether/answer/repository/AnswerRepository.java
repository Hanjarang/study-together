package helloworld.studytogether.answer.repository;

import helloworld.studytogether.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    int countByUserUserId(Long userId); // userId로 작성한 답변 개수 조회
    int countByUserUserIdAndIsSelectedTrue(Long userId); // userId로 채택된 답변 개수 조회

}