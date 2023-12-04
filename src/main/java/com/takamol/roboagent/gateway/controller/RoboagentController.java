package com.takamol.roboagent.gateway.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takamol.roboagent.gateway.models.ComplaintVO;
import com.takamol.roboagent.gateway.models.UserVO;
import com.takamol.roboagent.gateway.models.WebResponseBody;
import com.takamol.roboagent.gateway.utils.ComplaintUtils;
import com.takamol.roboagent.gateway.utils.JwtTokenUtil;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/RoboAgent/get-answer")
public class RoboagentController {
//
//	@Autowired
//	private ComplaintsRepository complaintsRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public static final Logger logger = LogManager.getLogger(RoboagentController.class);

	@PostMapping
	ResponseEntity<String> getAnswer(@RequestBody ComplaintVO newComplaint,
			@RequestHeader MultiValueMap<String, String> headers) {

		WebResponseBody responseBody = new WebResponseBody();

		try {

//			UserVO user = jwtTokenUtil.getUserFromToken(ComplaintUtils.removeFirstandLast(headers.get("authorization").get(0)));

//			if (user != null) {
			System.out.println("Resources\\RoboAgentFunc.exe " +newComplaint.getEstablishmentNumber() + newComplaint.getSelectedOption());
			Process process = new ProcessBuilder("Resources\\RoboAgentFunc.exe", newComplaint.getEstablishmentNumber(),
					newComplaint.getSelectedOption()).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			String answer = "";

			while ((line = br.readLine()) != null) {
				answer += line;
			}
			process.waitFor();
			String answer2 = process.getOutputStream().toString();
			
			System.out.println(answer2);
			process.destroy();
			System.out.println("Answer2 ===> " + answer2);
			responseBody.setResult(answer2);
			ComplaintUtils.responseGenerate(responseBody);
			System.out.printf("end ? ");
//			} else {
//				responseBody.setResult("104");// User not found
//				responseBody.setResultDescription("Failed - No user login");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);
	}

}
