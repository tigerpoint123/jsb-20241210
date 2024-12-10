package com.example.demo;

import com.example.demo.Entity.Question;
import com.example.demo.Repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void saveJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb가 뭐임");
		q1.setContent("알려줘");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링 질문");
		q2.setContent("id 자동생성 ?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
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

		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 뭐임", q.getSubject());

		}
	}

}
