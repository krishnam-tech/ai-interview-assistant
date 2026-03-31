package com.krishna.ai_interview_assistant.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import org.json.JSONObject;
import org.json.JSONArray;

@Service
public class GeminiService {

    private final String API_KEY ="AIzaSyC6dXB50HCSJSVXXO-Yf1MWJfIFQ7OZU2o";

    public String getFeedback(String question, String answer) {

        try {

            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

            
            RestTemplate restTemplate = new RestTemplate();

            String prompt =
                    "You are an interview evaluator.\n\n"
                            + "Question: " + question
                            + "\nCandidate Answer: " + answer
                            + "\n\nGive short feedback and score out of 10.";

            JSONObject part = new JSONObject();
            part.put("text", prompt);

            JSONArray parts = new JSONArray();
            parts.put(part);

            JSONObject content = new JSONObject();
            content.put("parts", parts);

            JSONArray contents = new JSONArray();
            contents.put(content);

            JSONObject requestBody = new JSONObject();
            requestBody.put("contents", contents);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request =
                    new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            JSONObject json = new JSONObject(response.getBody());

            return json
                    .getJSONArray("candidates")
                    .getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text");

        } catch (Exception e) {
            return "AI Feedback error: " + e.getMessage();
        }
    }
}