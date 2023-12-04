package com.takamol.roboagent.gateway.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.takamol.roboagent.gateway.models.UserVO;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
	@Query(value = "SELECT * FROM user u WHERE LOWER(u.email) = LOWER(:email) and u.password=:password", nativeQuery = true)
	public UserVO findByEmailAndPassword(String email, String password);

	@Query(value = "SELECT * FROM user u WHERE LOWER(u.email) = LOWER(:email)", nativeQuery = true)
	public UserVO findByEmail(String email);

	@Query(value = "SELECT COALESCE(max(user_id),0) FROM user", nativeQuery = true)
	public int getMaxUserId();
}