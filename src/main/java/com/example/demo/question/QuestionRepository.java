package com.example.demo.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // 구현을 하지 않더라도, JPA에서 리포지터리의 메서드명을 분석하여 쿼리를 만들고 실행하는 기능. findBy + 엔터티 속성명
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String question);
    List<Question> findBySubjectLike(String subject);
}
