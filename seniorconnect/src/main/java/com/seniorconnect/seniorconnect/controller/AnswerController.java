package com.seniorconnect.seniorconnect.controller;

import com.seniorconnect.seniorconnect.model.Answer;
import com.seniorconnect.seniorconnect.model.Question;
import com.seniorconnect.seniorconnect.model.User;

import com.seniorconnect.seniorconnect.repository.AnswerRepository;
import com.seniorconnect.seniorconnect.repository.QuestionRepository;
import com.seniorconnect.seniorconnect.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addAnswer(@RequestBody Map<String, String> req) {
        Question question = questionRepository.findById(Long.parseLong(req.get("questionId"))).orElse(null);
        User user = userRepository.findById(Long.parseLong(req.get("userId"))).orElse(null);

        if (question == null || user == null) {
            return ResponseEntity.badRequest().body("Invalid data");
        }

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setUser(user);
        answer.setContent(req.get("content"));

        answerRepository.save(answer);
        return ResponseEntity.ok("Answer added");
    }

    @GetMapping("/{questionId}")
    public List<Answer> getAnswers(@PathVariable Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}