package com.seniorconnect.seniorconnect.repository;

import com.seniorconnect.seniorconnect.model.SeniorProfile;
import com.seniorconnect.seniorconnect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SeniorProfileRepository extends JpaRepository<SeniorProfile, Long> {
    List<SeniorProfile> findByCompanyContainingIgnoreCase(String company);
    List<SeniorProfile> findByDomainContainingIgnoreCase(String domain);
    List<SeniorProfile> findByUser_Role(User.Role role);

    List<SeniorProfile> findByUser_RoleAndCompanyContainingIgnoreCase(User.Role role, String company);

    List<SeniorProfile> findByUser_RoleAndDomainContainingIgnoreCase(User.Role role, String domain);
    Optional<SeniorProfile> findByUserId(Long userId);
    Optional<SeniorProfile> findByUser(User user);
}