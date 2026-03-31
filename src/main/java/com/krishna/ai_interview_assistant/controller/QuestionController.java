package com.krishna.ai_interview_assistant.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.krishna.ai_interview_assistant.entity.Question;
import com.krishna.ai_interview_assistant.repository.QuestionRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuestionController {

    private final QuestionRepository repo;

    public QuestionController(QuestionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return repo.findAll();
    }
}