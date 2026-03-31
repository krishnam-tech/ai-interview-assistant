package com.krishna.ai_interview_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krishna.ai_interview_assistant.entity.InterviewResult;

public interface ResultRepository extends JpaRepository<InterviewResult, Long> {
}