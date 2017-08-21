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
import com.libertymutual.blackjack.models.Pot;

@Controller
@RequestMapping({"/", "/blackjack"})
public class BlackJackController {
	
	private Deck runningDeck; 
	private Dealer dealer; 
	private Gambler gambler; 
	private Pot pot; 
	
	public BlackJackController() {
		
		runningDeck = new Deck();
		dealer = new Dealer(); 
		gambler = new Gambler(); 
		pot = new Pot(); 
		
	}
	
	@GetMapping("")
	public String displayBlackjackForm(Model model) {
		double potValue = pot.getPotValue(); 
		Hand dealerHand = dealer.getDisplayCard();
		Hand gamblerHand = gambler.gethHand(); 
		model.addAttribute("gameMessage", "");
		model.addAttribute("dealerHand", dealerHand); 
		model.addAttribute("gamblerHand", gamblerHand);	
		model.addAttribute("dealerVisualHand", dealerHand.handDisplay()); 
		model.addAttribute("gamblerVisualHand", gamblerHand.handDisplay());
		model.addAttribute("potValue", potValue); 
		model.addAttribute("walletValue", gambler.getWalletValue()); 
		return "blackjack/blackjack-form"; 
	}
	
	@PostMapping("nexthand")
	public String nextHand(Model model) {
		model.addAttribute("dealerVisualHand", "");
		model.addAttribute("gamblerVisualHand", "");
		model.addAttribute("gameMessage", "");
		pot = new Pot(); 
		model.addAttribute("potValue", pot.getPotValue()); 	
		//model.addAttribute("dealerHand", ""); 
		//model.addAttribute("gamblerHand", "");	
		dealer = new Dealer(); 
		gambler.getNewHand(); 
		model.addAttribute("walletValue", gambler.getWalletValue()); 
		return "blackjack/blackjack-form";
	}
	
	@PostMapping("bet") 
	public String bet(Model model, double enteredValue) {
		model.addAttribute("gameMessage", "");
		if(enteredValue <=  gambler.getWalletValue()) {
			runningDeck.shuffle(); 
			gambler.placeBet(enteredValue);
			Card cardToDeal = runningDeck.getCard();
			pot.placeBet(enteredValue); 
			gambler.giveCard(cardToDeal);	
			cardToDeal = runningDeck.getCard(); 
			dealer.giveCard(cardToDeal);
			dealer.updateDisplayCard(cardToDeal);
			cardToDeal = runningDeck.getCard(); 
			gambler.giveCard(cardToDeal);
			cardToDeal = runningDeck.getCard(); 
			dealer.giveCard(cardToDeal);  
			if(pot.checkIfEndGame(gambler, dealer, false)) {
				model.addAttribute("gameMessage", "Game Over");
			}
			model.addAttribute("walletValue", gambler.getWalletValue()); 
			//model.addAttribute("canShowActionButtons", );
		}
		return "redirect:/blackjack"; 
	}
	
	@PostMapping("hit") 
	public String hit(Model model) {		
		Card cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);	
		//cardToDeal = runningDeck.getCard();
		if(pot.checkIfEndGame(gambler, dealer, false)) {
			model.addAttribute("gameMessage", "Game Over");
			model.addAttribute("walletValue", gambler.getWalletValue()); 
		}
		return "redirect:/blackjack";
	}
	
	@PostMapping("stand")
	public String stand(Model model) {
		//pot.checkIfEndGame(gambler,  dealer,  true); 
		dealer.finishHittingHand(runningDeck);
		if(pot.checkIfEndGame(gambler, dealer, true)) {
			model.addAttribute("gameMessage", "Game Over");
			model.addAttribute("walletValue", gambler.getWalletValue()); 
		}
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
