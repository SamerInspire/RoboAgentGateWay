package com.takamol.roboagent.gateway.models;

import java.util.Date;

public class LogsVO {

	private int ID;

	private String RequesterIP;
	private String Request;
	private String  Response;
	private Date CreationDate;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getRequesterIP() {
		return RequesterIP;
	}
	public void setRequesterIP(String requesterIP) {
		RequesterIP = requesterIP;
	}
	public String getRequest() {
		return Request;
	}
	public void setRequest(String request) {
		Request = request;
	}
	public String getResponse() {
		return Response;
	}
	public void setResponse(String response) {
		Response = response;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	@Override
	public String toString() {
		return "LogsVO [ID=" + ID + ", RequesterIP=" + RequesterIP + ", Request=" + Request + ", Response=" + Response
				+ ", CreationDate=" + CreationDate + "]";
	}
}

