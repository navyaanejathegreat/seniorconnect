package com.seniorconnect.seniorconnect.repository;

import com.seniorconnect.seniorconnect.model.MentorshipRequest;
import com.seniorconnect.seniorconnect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MentorshipRequestRepository extends JpaRepository<MentorshipRequest, Long> {
    List<MentorshipRequest> findByJunior(User junior);
    List<MentorshipRequest> findBySenior(User senior);
    List<MentorshipRequest> findByJuniorAndStatus(User junior, String status);
}