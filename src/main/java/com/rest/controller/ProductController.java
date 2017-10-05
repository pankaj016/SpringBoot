package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.rest.repository.ProductJpaRepository;

@RestController
public class ProductController /*implements ErrorController*/{

	 

    @Autowired
    ProductJpaRepository productJpaRepository;
   
    
    @RequestMapping(value = "product/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<Product> getAll() {    	
		return productJpaRepository.findAll();

	}
    
	 @RequestMapping(value = "product/findmatch/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
		public List<Product> getMatching(@PathVariable String name) {    	
			return productJpaRepository.findByMatchingProductList(name.toLowerCase() + "%");

     }
	 
	   @RequestMapping(value = "product/findmatch/{name}/{limit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
		public Page<Product> getMatchingLimit(@PathVariable String name,@PathVariable int limit) {    	
			return productJpaRepository.findByMatchingProductPagedList( new  PageRequest(0, limit) ,name.toLowerCase() + "%");

      }
	 
    
     @RequestMapping(value = "product/add" , method = RequestMethod.POST)
    public List<Product> add(@RequestBody final Product product) {
    	  	
    	productJpaRepository.save(product);
    	return productJpaRepository.findByMatchingProductList(product.getProduct_name());
    }
}