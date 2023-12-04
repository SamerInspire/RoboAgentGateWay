package com.takamol.roboagent.gateway.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.takamol.roboagent.gateway.models.ComplaintVO;

//
//@Repository
//public interface ComplaintsRepository extends PagingAndSortingRepository<ComplaintVO, Long> {
//	@Query(value = "SELECT * FROM complaint c WHERE c.user_id = :userId", nativeQuery = true)
//	public List<ComplaintVO> findByUserID(String userId);
//
//	@Query(value = "SELECT * FROM complaint c WHERE c.user_id = :userId", nativeQuery = true)
//	public Page<ComplaintVO> getPageComplaintByUserId(String userId, Pageable pageable);
//
//	@Query(value = "SELECT COALESCE(max(complaint_Id),0) FROM complaint", nativeQuery = true)
//	public Long getMaxComplaintId();
//
//}