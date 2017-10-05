package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Company;
import com.rest.repository.CompanyJpaRepository;

@RestController
public class CompanyController implements ErrorController{

	 private static final String PATH = "/error";

	    @RequestMapping(value = PATH)
	    public String error() {
	        return "<STYLE>#bg-text{ color:white;    font-size:40px;  }</STYLE><body background=\"http://nationwideradiojm.com/wp-content/uploads/2015/11/access-denied-715x400.png\""
	        		+ "><H1 id=\"bg-text\">\tError:409 <BR><BR>#WRONG ENDPOINT# Enter a Valid REST End Point";
	    }

	    @Override
	    public String getErrorPath() {
	        return PATH;
	    }

    @Autowired
    CompanyJpaRepository companyJpaRepository;
   
    
    @RequestMapping(value = "company/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Company> getAll() {    	
		return companyJpaRepository.findAll();

	}
    
	 @RequestMapping(value = "company/findmatch/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
		public List<Company> getMatching(@PathVariable String name) {    	
			return companyJpaRepository.findByMatchingCompanyList(name.toLowerCase() + "%");

		} 
	 
	 @RequestMapping(value = "company/findmatch/{name}/{limit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
		public Page<Company> getMatchingTop10(@PathVariable String name,@PathVariable int limit) {    	
			return companyJpaRepository.findMyCompanyTop10List(new PageRequest(0, limit),name.toLowerCase() + "%");

		} 
	 
    
    @RequestMapping(value = "company/add" , method = RequestMethod.POST)
    public List<Company> add(@RequestBody final Company company) {
    	  	
    	companyJpaRepository.save(company);
    	return companyJpaRepository.findByMatchingCompanyList(company.getCompany_name());
    }
}