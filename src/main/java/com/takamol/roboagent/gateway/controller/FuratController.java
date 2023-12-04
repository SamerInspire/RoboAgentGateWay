package com.takamol.roboagent.gateway.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import com.takamol.roboagent.gateway.ProjectStarter;
import com.takamol.roboagent.gateway.models.TestVO;
import com.takamol.roboagent.gateway.service.TestRepository;


@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/Furat/get-answer")
public class FuratController {
	public static final Logger logger = LogManager.getLogger(FuratController.class);
	
	@Autowired
	private TestRepository testRepo;
	
	@GetMapping()
	public ResponseEntity<String> signUp() {
		
		List<TestVO> all = testRepo.findAll();
		logger.error("this is a test");
		System.out.println("all ===> " + all.toString());
		logger.error("all of test data is " + all.toString());
		return ResponseEntity.ok().body("all of test data is " + all.toString());

	}
}
