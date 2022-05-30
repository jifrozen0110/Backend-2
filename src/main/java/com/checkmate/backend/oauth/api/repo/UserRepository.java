package com.checkmate.backend.oauth.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkmate.backend.oauth.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

	int countByUsername(String username);

	int countByUserId(String userId);

}
