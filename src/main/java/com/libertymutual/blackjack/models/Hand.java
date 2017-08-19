package com.libertymutual.blackjack.models;

import java.util.ArrayList;
import java.util.List;


public class Hand {
	
	ArrayList<Card> cards; 
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card); 
	}
	
	public List<Card> getCards() {
		return cards; 
	}
	
	public int[] getValues() {
		int[] sums = new int[] {0, 0}; 
		
		for (Card c : cards) {
			int[] values = c.getValues(); 
			sums[0] += values[0]; 
			sums[1] += values[1]; 
		}
		
		return sums; 
	}
	
	public String handDisplay() {
		String visualHand = "";
		
		if(cards.isEmpty()) {
			return visualHand; 
		}
		
		visualHand += cards.get(0).getVisualRepresentation();
		
		for(int i = 1; i < cards.size(); i += 1) { 
			visualHand += ", " + cards.get(i).getVisualRepresentation(); 
		}
		
		return visualHand; 
	}
		
}
			
