package com.krishna.ai_interview_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krishna.ai_interview_assistant.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}