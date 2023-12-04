package com.takamol.roboagent.gateway.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takamol.roboagent.gateway.models.UserVO;
import com.takamol.roboagent.gateway.models.WebResponseBody;
import com.takamol.roboagent.gateway.service.UserRepository;
import com.takamol.roboagent.gateway.utils.ComplaintUtils;
import com.takamol.roboagent.gateway.utils.JwtTokenUtil;
import com.mysql.cj.util.StringUtils;

@CrossOrigin(origins = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Validated @RequestBody UserVO user) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WebResponseBody responseBody = new WebResponseBody();
		try {

			responseBody.setComplaints(null);

			responseBody.setResultDescription(ComplaintUtils.checkUserValidation(user, userRepository));

			if (!StringUtils.isEmptyOrWhitespaceOnly(responseBody.getResultDescription())) {
				responseBody.setResult("103");

				return ComplaintUtils.responseGenerate(responseBody);
			}

			user.setIsAdmin(false);
//			user.setUserId(userRepository.getMaxUserId() + 1);
//			user.setPassword(JwtTokenUtil.encryptData(user.getPassword()));
			userRepository.save(user);

			responseBody.setUser(user);

			responseBody.setResult("0");
			responseBody.setResultDescription("Success");

		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);

	}

	@PostMapping("/signupforadmin")
	public ResponseEntity<String> signUpForAdmin(@RequestBody UserVO user) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebResponseBody responseBody = new WebResponseBody();
		try {
			responseBody.setComplaints(null);

			responseBody.setResultDescription(ComplaintUtils.checkUserValidation(user, userRepository));

			if (!StringUtils.isEmptyOrWhitespaceOnly(responseBody.getResultDescription())) {
				responseBody.setResult("103");

				return ComplaintUtils.responseGenerate(responseBody);
			}

			user.setIsAdmin(true);
//			user.setUserId(userRepository.getMaxUserId() + 1);
//			user.setPassword(JwtTokenUtil.encryptData(user.getPassword()));
			userRepository.save(user);

			responseBody.setResult("0");
			responseBody.setResultDescription("Success");
			responseBody.setUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);

	}

	@GetMapping("/currentuser")
	public ResponseEntity<String> currentUser(@RequestHeader MultiValueMap<String, String> headers) {
		WebResponseBody responseBody = new WebResponseBody();
		try {
			responseBody.setComplaints(null);
			String token = headers.get("authorization").get(0);
			token = token.startsWith("\"") ? ComplaintUtils.removeFirstandLast(token) : token;
			UserVO user = jwtTokenUtil.getUserFromToken(token);
			if (user != null && user.getUserName() != null) {
				responseBody.setUser(user);
				responseBody.setResult("0");
				responseBody.setResultDescription("Success");
			} else {
				responseBody.setResult("101");
				responseBody.setResultDescription("Failed - Invalied JWT Token");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}
		return ComplaintUtils.responseGenerate(responseBody);
	}

	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody UserVO user) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WebResponseBody responseBody = new WebResponseBody();
		responseBody.setComplaints(null);

		try {
//			user.setPassword(JwtTokenUtil.encryptData(user.getPassword()));
//			user = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

			if (user != null) {
				responseBody.setResult("0");
				responseBody.setResultDescription("Success");
				responseBody.setUser(user);
			} else {
				responseBody.setResult("102");
				responseBody.setResultDescription("Failed - User/Password not correct");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ComplaintUtils.responseGenerate(responseBody);
		}

		return ComplaintUtils.responseGenerate(responseBody);
	}
}
