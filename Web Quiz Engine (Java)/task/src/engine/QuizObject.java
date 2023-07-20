package engine;
import com.fasterxml.jackson.annotation.JsonView;
import io.micrometer.core.lang.Nullable;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


public class QuizObject {

    @JsonView(View.Base.class)
    private Long id;
    @NotBlank
    @JsonView(View.Base.class)
    private String title;
    @NotBlank
    @JsonView(View.Base.class)
    private String text;
    @NotNull
    @Size(min = 2)
    @JsonView(View.Base.class)
    private ArrayList<String> options;
    @JsonView(View.Admin.class)
    private List<Integer> answer;

    public QuizObject(String title, String text, ArrayList<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public QuizObject(Long id, String title, String text, ArrayList<String> options, ArrayList<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public QuizObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}


