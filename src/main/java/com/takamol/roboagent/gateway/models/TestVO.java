package com.takamol.roboagent.gateway.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Test")
public class TestVO {
	@Id
	private int TestID ;
    @Column(name = "testname")
    private String TestName ;
    @Column(name = "testlink")
    private String TestLink ;
    @Column(name = "testlocation")
    private String TestLocation ;
    
	public int getTestID() {
		return TestID;
	}
	public void setTestID(int testID) {
		TestID = testID;
	}
	public String getTestName() {
		return TestName;
	}
	public void setTestName(String testName) {
		TestName = testName;
	}
	public String getTestLink() {
		return TestLink;
	}
	public void setTestLink(String testLink) {
		TestLink = testLink;
	}
	public String getTestLocation() {
		return TestLocation;
	}
	public void setTestLocation(String testLocation) {
		TestLocation = testLocation;
	}
	@Override
	public String toString() {
		return "Test [TestID=" + TestID + ", TestName=" + TestName + ", TestLink=" + TestLink + ", TestLocation="
				+ TestLocation + "]";
	}

}
