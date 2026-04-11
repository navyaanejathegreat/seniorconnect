package com.seniorconnect.seniorconnect.repository;

import com.seniorconnect.seniorconnect.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByOrderByCreatedAtDesc();
}