package com.takamol.roboagent.gateway.models;

import java.util.Arrays;

import javax.persistence.Entity;

public class ComplaintVO {

	private Long transactionId;

	private String user;
	private String email;
	private String selectedOption;
	private String establishmentNumber;
	private String idNumber;
	private String[] iqamehNumber;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getEstablishmentNumber() {
		return establishmentNumber;
	}
	public void setEstablishmentNumber(String establishmentNumber) {
		this.establishmentNumber = establishmentNumber;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String[] getIqamehNumber() {
		return iqamehNumber;
	}
	public void setIqamehNumber(String[] iqamehNumber) {
		this.iqamehNumber = iqamehNumber;
	}
	@Override
	public String toString() {
		return "ComplaintVO [transactionId=" + transactionId + ", user=" + user + ", email=" + email
				+ ", selectedOption=" + selectedOption + ", establishmentNumber=" + establishmentNumber + ", idNumber="
				+ idNumber + ", iqamehNumber=" + Arrays.toString(iqamehNumber) + "]";
	}
	
}
