package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class S_User {
	
	/*PID NUMBER(5),
    LastName varchar(255),
    FirstName varchar(255),
    sdxFN varchar(255),
    sdxLN varchar(255)*/
	
	@Column(name="PID")
	private long pID;
	private String lastName;
	private String firstName;
	private String sdxFN;
	private String sdxLN;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)	
	public long getPID() {
		return this.pID;
	}
	public void setPID(long pID) {
		this.pID = pID;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSdxFN() {
		return sdxFN;
	}
	public void setSdxFN(String sdxFN) {
		this.sdxFN = sdxFN;
	}
	public String getSdxLN() {
		return sdxLN;
	}
	public void setSdxLN(String sdxLN) {
		this.sdxLN = sdxLN;
	}


}
