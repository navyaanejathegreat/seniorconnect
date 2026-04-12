package com.seniorconnect.seniorconnect.controller;
import com.seniorconnect.seniorconnect.repository.QuestionRepository;

import com.seniorconnect.seniorconnect.model.Question;
import com.seniorconnect.seniorconnect.model.User;
import com.seniorconnect.seniorconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forum")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(@RequestBody Map<String, String> req) {
        User user = userRepository.findById(Long.parseLong(req.get("userId"))).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("User not found");

        Question question = new Question();
        question.setAskedBy(user);
        question.setTitle(req.get("title"));
        question.setBody(req.get("body"));
        questionRepository.save(question);

        return ResponseEntity.ok("Question posted!");
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAllByOrderByCreatedAtDesc();
    }
}