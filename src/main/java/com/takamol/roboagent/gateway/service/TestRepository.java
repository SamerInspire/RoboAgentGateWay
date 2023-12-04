package com.takamol.roboagent.gateway.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.takamol.roboagent.gateway.models.TestVO;


@Repository
public interface TestRepository extends JpaRepository<TestVO, Integer>{
	@Query(value = "SELECT * FROM Test", nativeQuery = true)
	public List<TestVO> getAllFromTest();


}