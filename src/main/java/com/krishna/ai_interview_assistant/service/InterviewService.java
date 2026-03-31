package com.krishna.ai_interview_assistant.service;

import com.krishna.ai_interview_assistant.entity.Question;
import com.krishna.ai_interview_assistant.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}