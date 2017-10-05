package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
	
	/*cid INT,
	company_name VARCHAR(50),
	head_office VARCHAR(50),
  PRIMARY KEY (cid)*/
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long cid;
	@Column(name="company_name")
	private String company_name;
	@Column(name="head_office")
	private String head_office;
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getHead_office() {
		return head_office;
	}
	public void setHead_office(String head_office) {
		this.head_office = head_office;
	}
	
	

}
