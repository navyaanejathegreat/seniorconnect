package com.seniorconnect.seniorconnect.repository;

import com.seniorconnect.seniorconnect.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByCreatedAt(
            Long s1, Long r1, Long s2, Long r2
    );
}