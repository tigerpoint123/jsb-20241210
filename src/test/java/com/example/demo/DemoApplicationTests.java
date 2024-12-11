package com.example.demo;

import com.example.demo.answer.Answer;
import com.example.demo.question.Question;
import com.example.demo.answer.AnswerRepository;
import com.example.demo.question.QuestionRepository;
import com.example.demo.question.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class DemoApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    @Test
    void saveJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터 : [%03d]", i);
            String content = "내용무";

            this.questionService.create(subject, content, null);
        }
//        Question q1 = new Question();
//        q1.setSubject("sbb가 뭐임");
//        q1.setContent("알려줘");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링 질문");
//        q2.setContent("id 자동생성 ?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2);
    }

    @Test
    void findAllJpa() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 뭐임", q.getSubject());
    }

    @Test
    void findByIdJpa() {
        Optional<Question> oq = this.questionRepository.findById(1);

        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 뭐임", q.getSubject());

        }
    }

    @Test
    void findBySubjectJpa() {
        Question q = this.questionRepository.findBySubject("스프링 질문");
        assertEquals(2, q.getId());
    }

    @Test
    void findBySubjectAndContentJpa() {
        Question q = this.questionRepository.findBySubjectAndContent("스프링 질문", "id 자동생성 ?");
        assertEquals(1, q.getId());
    }

    @Test
    void findBySubjectLike() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 뭐임", q.getSubject());
    }

    @Test
    void modifyJpa() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void deleteJpa() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @Test
    void saveAnswerJpa() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    void findAnswerJpa() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @Test
    void findQuestionWithAnswerJpa() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다." , answerList.get(0).getContent());
    }
}
