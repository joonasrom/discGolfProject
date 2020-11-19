package com.example.discGolfProject.web;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.discGolfProject.model.Round;
import com.example.discGolfProject.model.RoundRepository;
import com.example.discGolfProject.model.TrackRepository;
import com.example.discGolfProject.model.User;
import com.example.discGolfProject.model.UserRepository;
import com.example.discGolfProject.service.DgService;
import com.example.discGolfProject.model.SignupForm;

@Controller
public class DgController {
	@Autowired
	private RoundRepository rrepository;

	@Autowired
	private TrackRepository trepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private DgService dgservice;

	@RequestMapping(value = "/roundlist", method = RequestMethod.GET)
	public String roundList(Model model) {
		model.addAttribute("rounds", rrepository.findAll());
		return "roundlist";
	}
	
	//Delete round by round id. Only accessible in admin mode. 

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteRound(@PathVariable("id") Long roundId, Model model) {

		rrepository.deleteById(roundId);

		return "redirect:../roundlist";
	}

	//Saves round on given informations to round list.
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Round round) {
		rrepository.save(round);

		return "redirect:roundlist";
	}
	
	
	// Saves user on given informations to users.
    		@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupForm") SignupForm signupForm, BindingResult bindingResult) {
        String emailTest = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (!bindingResult.hasErrors()) { 
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {   
            	
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodedPwd = encoder.encode(pwd);
                
                Pattern pattern = Pattern.compile(emailTest);
                Matcher matcher = pattern.matcher(signupForm.getEmail());
                if(!matcher.matches()) {
                    bindingResult.rejectValue("email", "err.email", "Email doesn't meet requirements");
                    return "adduser";
                }
    
                
                User userToAdd = new User();
                userToAdd.setPasswordHash(encodedPwd);
                userToAdd.setUsername(signupForm.getUsername());
                userToAdd.setEmail(signupForm.getEmail());
                userToAdd.setRole("USER");
                
                if (urepository.findByUsername(signupForm.getUsername()) == null) {
                    urepository.save(userToAdd);
                }
                
                else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");        
                    return "adduser";                    
                }
            }
            
            else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");        
                return "adduser";
            }
        }
        else {
            return "adduser";
        }
        return "redirect:/login";        
    }
    		
	
	//Sets sign up to be ready for new user
	@RequestMapping(value = "/adduser")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

	//Sets add round to be ready for new round
	@RequestMapping(value = "/add")
	public String addRound(Model model) {
		model.addAttribute("round", new Round());
		model.addAttribute("tracks", trepository.findAll());
		return "addround";
	}

	//Sets edit round to be ready to edit existing round by round id.
	@RequestMapping(value = "/editround/{id}", method = RequestMethod.GET)
	public String addRound(@PathVariable("id") Long roundId, Model model) {
		model.addAttribute("round", rrepository.findById(roundId));
		model.addAttribute("tracks", trepository.findAll());
		return "editround";

	}
	
	//Gets searchword and sets model to get all matching info
	@RequestMapping(value = "/searchround{searchword}")
	public String searchRound(Model model, String searchword) {
		
		if(searchword != null) {
			model.addAttribute("rounds", dgservice.findBySearchword(searchword));
		} else {
			model.addAttribute("rounds", rrepository.findAll());
		}
		
		return "searchround";
	}
	

    @RequestMapping(value="/rounds", method = RequestMethod.GET)
    public @ResponseBody List<Round> roundListRest() {	
        return (List<Round>) rrepository.findAll();
    }    

	
    @RequestMapping(value="/round/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Round> findroundRest(@PathVariable("id") Long id) {	
    	return rrepository.findById(id);
    }     
    
    @RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
    
    
}