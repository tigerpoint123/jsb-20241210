package com.example.demo.question;

import com.example.demo.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(int id) {
        Optional<Question> question = this.questionRepository.findById(id);

        if(question.isPresent()) {
            return question.get();
        } else throw new DataNotFoundException("question not found");
    }
}
