package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", "/blackjack"})
public class BlackJackController {
	
	private Deck runningDeck; 
	private Dealer dealer; 
	private Gambler gambler; 
	
	public BlackJackController() {
		
		runningDeck = new Deck();
		dealer = new Dealer(); 
		gambler = new Gambler(); 
		
	}
	
	@GetMapping("")
	public ModelAndView displayBlackjackForm() {
		Hand hand = new Hand(); 
		ModelAndView mv = new ModelAndView("blackjack/blackjack-form"); 
		mv.addObject("hand", hand); 
		return mv; 
	}
	
//	@PostMapping("deal")
//	public ModelAndView deal() {	
//		Hand hand = new Hand(); 
//		ModelAndView mv = new ModelAndView("blackjack/blackjack-form"); 
//		mv.addObject("hand", hand); 
//		return mv; 
//		
//	}
	
	@PostMapping("bet") 
	public String bet() {
		Card cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);	
		cardToDeal = runningDeck.getCard(); 
		dealer.giveCard(cardToDeal); 
		cardToDeal = runningDeck.getCard(); 
		gambler.giveCard(cardToDeal);
		cardToDeal = runningDeck.getCard(); 
		dealer.giveCard(cardToDeal); 
		return "redirect:/blackjack"; 
	}
	
	@PostMapping("hit") 
	public String hit() {
		return "redirect:/blackjack";
	}
	
	@PostMapping("stand")
	public String stand() {
		return "redirect:/blackjack"; 
	}
	
	@PostMapping("rules")
	public String rules() {
		return "blackjack/rules";
	}
	
	@PostMapping("/rules/returnhome")
	public String returnHome() {
		return "redirect:/blackjack";
	}
	
}
