package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.libertymutual.blackjack.models.Card;
import com.libertymutual.blackjack.models.Dealer;
import com.libertymutual.blackjack.models.Deck;
import com.libertymutual.blackjack.models.Gambler;
import com.libertymutual.blackjack.models.Hand;

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
	public String displayBlackjackForm(Model model) {
		Hand dealerHand = dealer.getDisplayCard();
		Hand gamblerHand = gambler.gethHand(); 
		model.addAttribute("dealerHand", dealerHand); 
		model.addAttribute("gamblerHand", gamblerHand);	
		model.addAttribute("dealerVisualHand", dealerHand.handDisplay()); 
		model.addAttribute("gamblerVisualHand", gamblerHand.handDisplay()); 
		return "blackjack/blackjack-form"; 
	}
	
	@PostMapping("nexthand")
	public String nextHand() {	
		dealer = new Dealer(); 
		gambler = new Gambler();		
		return "blackjack/blackjack-form";
	}
	
	@PostMapping("bet") 
	public String bet() {
		runningDeck.shuffle(); 
		Card cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);	
		cardToDeal = runningDeck.getCard(); 
		dealer.giveCard(cardToDeal); 
		dealer.updateDisplayCard(cardToDeal);
		cardToDeal = runningDeck.getCard(); 
		gambler.giveCard(cardToDeal);
		cardToDeal = runningDeck.getCard(); 
		dealer.giveCard(cardToDeal); 
		return "redirect:/blackjack"; 
	}
	
	@PostMapping("hit") 
	public String hit() {		
		Card cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);	
		//cardToDeal = runningDeck.getCard();
		return "redirect:/blackjack";
	}
	
	@PostMapping("stand")
	public String stand() {
		dealer.finishHittingHand(runningDeck);
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
