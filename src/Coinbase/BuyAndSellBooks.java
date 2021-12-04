package Coinbase;

import java.util.*;
/*
 * https://www.1point3acres.com/bbs/thread-825567-1-1.html
 * 
Buy and sell books. Follow-up: if with expiration time
You operate a marketplace for buying & selling used textbooks. For a given textbook, e.g. “Theory of Cryptography,” there are people who want to buy this textbook and people who want to sell.
  Offers to BUY: $100, $100, $99, $99, $97, $90
  Offers to SELL: $109, $110, $110, $114, $115, $119
A match occurs when two people agree on a price. Some new offers do not match. These offers should be added to the active set of offers. For example:
  Tim offers to SELL at $150. This will not match. No one is willing to buy at that price so we save the offer.
  Offers to SELL:: $109, $110, $110, $114, $115, $119, $150
When matching we want to give the customer the “best price”. Example matches:
  If Jane offers to BUY at $120, she will match and buy a book for $109 (the lowest offer to sell is the best price). The sell offers should be updated to reflect the match
  Offers to SELL: $110, $110, $114, $115, $119, $150
If Connie offers to SELL at $99 she will match and sell her book for $100 (the highest offer to buy is the best price). The buy offers should be updated to reflect the match
  Offers to BUY: $100, $99, $99, $97, $90
Our system will need to:
  Accept incoming offers to buy & sell
  Output if the price matches
  Keep an updated collections of buys & sells
Follow up: implement an order which can expire，e.g. buyer’s order valid for 10 min，and then auto expire
 * 
 * 
 */

public class BuyAndSellBooks {
	enum Type {
		BUY,
		SELL
	}
	static class Request {
		Type type;
		int price;
		Request(Type type, int price) {
			this.type = type;
			this.price = price;
		}
	}
	static PriorityQueue<Integer> min = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> max = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
		public int compare(Integer left, Integer right) {
			return right - left;
		}
	});
	static List<Integer> buys = new ArrayList<Integer>();
	static List<Integer> sells = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		basicProblem();
	}
	
	public static void basicProblem() {
		// Buy $100, $100, $99, $99, $97, $90
		Request r1 = new Request(Type.BUY, 100);
		buyAndSellBooks(r1);
		Request r2 = new Request(Type.BUY, 100);
		buyAndSellBooks(r2);
		Request r3 = new Request(Type.BUY, 99);
		buyAndSellBooks(r3);
		Request r4 = new Request(Type.BUY, 99);
		buyAndSellBooks(r4);
		Request r5 = new Request(Type.BUY, 97);
		buyAndSellBooks(r5);
		Request r6 = new Request(Type.BUY, 90);
		buyAndSellBooks(r6);
		// print
		printBuySellList();
		
		// SELL: $109, $110, $110, $114, $115, $119
		Request r7 = new Request(Type.SELL, 109);
		buyAndSellBooks(r7);
		Request r8 = new Request(Type.SELL, 110);
		buyAndSellBooks(r8);
		Request r9 = new Request(Type.SELL, 110);
		buyAndSellBooks(r9);
		Request r10 = new Request(Type.SELL, 114);
		buyAndSellBooks(r10);
		Request r11 = new Request(Type.SELL, 115);
		buyAndSellBooks(r11);
		Request r12 = new Request(Type.SELL, 119);
		buyAndSellBooks(r12);
		// print
		printBuySellList();
		
		Request r13 = new Request(Type.SELL, 150);
		buyAndSellBooks(r13);
		printBuySellList();
		
		Request r14 = new Request(Type.BUY, 120);
		buyAndSellBooks(r14);
		printBuySellList();
		
		Request r15 = new Request(Type.SELL, 99);
		buyAndSellBooks(r15);
		printBuySellList();

	}
	
	public static void buyAndSellBooks(Request request) {
		if (request.type == Type.BUY) {
			if (!min.isEmpty() && min.peek() <= request.price) {
				int sellPrice = min.poll();
				sells.remove(new Integer(sellPrice));
				System.out.println("Buy Request -- buy:" + request.price + " sell:" + sellPrice);
			} else {
				max.add(request.price);
				buys.add(request.price);
			}
		} else if (request.type == Type.SELL) {
			if (!max.isEmpty() && max.peek() >= request.price) {
				int buyPrice = max.poll();
				buys.remove(new Integer(buyPrice));
				System.out.println("Sell Request -- buy:" + buyPrice + " sell:" + request.price);
			} else {
				min.add(request.price);
				sells.add(request.price);
			}
		}
	}
	
	public static void printBuySellList() {
		System.out.println("******************************");
		System.out.println("Buy List:");
		for (int i = 0; i < buys.size(); i++) {
			System.out.print(buys.get(i) + " ");
		}
		System.out.println();
		System.out.println("Sell List:");
		for (int i = 0; i < sells.size(); i++) {
			System.out.print(sells.get(i) + " ");
		}
		System.out.println();
		System.out.println("******************************");
	}
}
