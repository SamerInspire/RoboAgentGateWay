package com.takamol.roboagent.gateway.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class WebResponseBody {

	private UserVO user;
	private List<ComplaintVO> complaints = new ArrayList<ComplaintVO>();
	private Page<ComplaintVO> complaintsPage = null;
	private String result;
	private String resultDescription;

	public List<ComplaintVO> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<ComplaintVO> complaints) {
		this.complaints = complaints;
	}

	public Page<ComplaintVO> getComplaintsPage() {
		return complaintsPage;
	}

	public void setComplaintsPage(Page<ComplaintVO> complaintsPage) {
		this.complaintsPage = complaintsPage;
	}

	public UserVO getUser() {
		return user;
	}
	
	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	@Override
	public String toString() {
		return "WebResponseBody [user=" + user + ", result=" + result + ", resultDescription=" + resultDescription
				+ "]";
	}

}
