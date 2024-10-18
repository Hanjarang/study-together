package helloworld.studytogether.questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import helloworld.studytogether.questions.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    int countByUserUserId(Long userId); // userId로 작성한 글 개수 조회
}
