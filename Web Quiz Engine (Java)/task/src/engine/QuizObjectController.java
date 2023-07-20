package engine;
import com.fasterxml.jackson.annotation.JsonView;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class QuizObjectController {
private final QuizService quizService;



@Autowired
    public QuizObjectController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping(path = "/api/quizzes", produces = "application/json")
    public ResponseEntity<QuizObject> createNewQuiz (@Valid @RequestBody QuizObject quizObject) {
     return quizService.createNewQuiz(quizObject);
    }

    @GetMapping("/api/quizzes")
    @JsonView(View.Base.class)
    public List<QuizObject> getQuizzes() {
    return quizService.getQuizzes();
    }


    @GetMapping ("/api/quizzes/{id}")
    @JsonView(View.Base.class)
    public QuizObject getQuizById(@PathVariable Long id){
        return quizService.getQuizById(id);
    }


   @PostMapping(path="api/quizzes/{id}/solve", produces = "application/json")
    public QuizResponse solveQuiz(
            @PathVariable Long id,
            @Valid @RequestParam(value = "answer", required = false) List<Integer> answer) {
        return quizService.solveQuiz(id, answer);
    }


}


