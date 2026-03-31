package com.krishna.ai_interview_assistant.controller;

import com.krishna.ai_interview_assistant.dto.AnswerRequest;
import com.krishna.ai_interview_assistant.entity.Question;
import com.krishna.ai_interview_assistant.repository.QuestionRepository;
import com.krishna.ai_interview_assistant.service.GeminiService;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin
public class InterviewController {

    private final QuestionRepository questionRepository;
    private final GeminiService geminiService;

    public InterviewController(QuestionRepository questionRepository,
                               GeminiService geminiService){
        this.questionRepository = questionRepository;
        this.geminiService = geminiService;
    }

    @GetMapping("/question")
    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    @PostMapping("/answer")
    public Map<String,String> submitAnswer(@RequestBody AnswerRequest request){

        Question question =
        questionRepository.findById(request.getQuestionId()).orElse(null);

        String feedback =
        geminiService.getFeedback(
        question.getQuestionText(),
        request.getAnswer()
        );

        Map<String,String> response = new HashMap<>();

        response.put("feedback",feedback);

        return response;
    }
}
