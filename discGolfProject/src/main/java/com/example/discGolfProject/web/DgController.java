package com.example.discGolfProject.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.discGolfProject.model.Round;
import com.example.discGolfProject.model.RoundRepository;
import com.example.discGolfProject.model.TrackRepository;


@Controller
public class DgController {
	@Autowired
	private RoundRepository rrepository;

	@Autowired
	private TrackRepository trepository;

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

	
	@RequestMapping(value = "/add")
	public String addRound(Model model) {
		model.addAttribute("round", new Round());
		model.addAttribute("tracks", trepository.findAll());
		return "addround";
	}

	@RequestMapping(value = "/editround/{id}", method = RequestMethod.GET)
	public String addRound(@PathVariable("id") Long roundId, Model model) {
		model.addAttribute("round", rrepository.findById(roundId));
		model.addAttribute("tracks", trepository.findAll());
		return "editround";

	}

	

    @RequestMapping(value="/rounds", method = RequestMethod.GET)
    public @ResponseBody List<Round> roundListRest() {	
        return (List<Round>) rrepository.findAll();
    }    

	
    @RequestMapping(value="/round/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Round> findroundRest(@PathVariable("id") Long id) {	
    	return rrepository.findById(id);
    }     
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchByPlayer(Model model, @RequestParam(defaultValue="") String name) {
		model.addAttribute("players", trepository.findByName(name));
		return "searchround";

	}
}