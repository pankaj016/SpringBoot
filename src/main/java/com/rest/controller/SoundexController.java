package com.rest.controller;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.S_User;
import com.rest.helper.SoundexAlgorithm;
import com.rest.repository.S_UserJpaRepository;

@RestController
public class SoundexController {

    private static final String PATH = "/error";

    @Autowired
    S_UserJpaRepository s_UserJpaRepository;
    
  
    @RequestMapping(value = "soundex/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<S_User> getAll() {
    	
		return s_UserJpaRepository.findAll();

	}
    
	 @RequestMapping(value = "soundex/findexactmatch/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
		public List<S_User> getMatching(@PathVariable String name) {    	
			return s_UserJpaRepository.findByMatchingUserList(SoundexAlgorithm.getSoundex(name));

		}
   
    @RequestMapping("soundex/findcode")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return SoundexAlgorithm.getSoundex(name);
    }
    
    @RequestMapping(value = "soundex/findbyname/{name}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<S_User> getSoundexMatching(@PathVariable String name) {
    	return s_UserJpaRepository.findBySoundexUserList(  name.toLowerCase() + "%");
    }
  
    
    @RequestMapping(value = "soundex/remove/{pid}" , method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeSoundexByID(@PathVariable long pid) {    	
    	s_UserJpaRepository.delete(pid);
    }
   
   @RequestMapping("/info")
	    public String greetinga(@RequestParam(value="name", defaultValue="World") String name) throws Exception{
	        return "<body  background=\"http://comunytek.com/wp-content/uploads/2017/03/Microservices.png\">"
	        		+ "<STYLE>#bg-text{ color:lightgrey;    font-size:40px;  }</STYLE><H1 id=\"bg-text\">"
	        		+ "<BR>"
	        		
	        		+ "Served by the clustered Server <BR>"+InetAddress.getLocalHost().getHostName()+"<BR>"
	        		+ "</body>";
	    }
	 
	 
    
    @RequestMapping(value = "soundex/add" , method = RequestMethod.POST)
    public List<S_User> add(@RequestBody final S_User user) {
    	user.setSdxFN(SoundexAlgorithm.getSoundex(user.getFirstName()));
    	user.setSdxLN(SoundexAlgorithm.getSoundex(user.getLastName()));    	
    	s_UserJpaRepository.save(user);
    	return s_UserJpaRepository.findByFirstName(user.getFirstName());
    }
    
    
}