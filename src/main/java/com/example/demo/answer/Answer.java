package com.example.demo.answer;

import com.example.demo.question.Question;
import com.example.demo.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne // N:1 관계 설정.
    private Question question;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

}
