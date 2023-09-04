package engine;

import io.micrometer.core.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@Service
public class QuizService {

private List <QuizObject> quizList = new ArrayList<>();
private long nextId = 1;

    public ResponseEntity <QuizObject> createNewQuiz(@RequestBody QuizObject quizObject) {
            if (quizObject.getAnswer() == null) {
                quizObject.setAnswer(new int[0]);
            }
            QuizObject newQuiz = new QuizObject(nextId++,quizObject.getTitle(),quizObject.getText(),quizObject.getOptions(), quizObject.getAnswer());

            quizList.add(newQuiz);

        return new ResponseEntity<>(newQuiz, HttpStatus.OK);
    }


    public List<QuizObject> getQuizzes() {
        return quizList;
    }


    public QuizObject getQuizById(Long id) {
        for (QuizObject quiz : quizList) {
            if (quiz.getId() == id){
                return quiz;
            }
        }
        throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Not found"
        );
    }


    public QuizResponse solveQuiz(Long id, UserAnswer userAnswer) {

        QuizObject quiz = getQuizById(id);
        boolean success = Arrays.equals(quiz.getAnswer(), userAnswer.getAnswer());
        String feedback = success ? "Congratulations, you're right!" : "Wrong answer! Please try again.";
        return new QuizResponse(success, feedback);

    }


}
