package program;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HashMapTest {
	
	public static void main(String[] args) {
		Map<String, String> capitals = new HashMap<String, String>();
		capitals.put("BRASIL", "Brasilia");
		capitals.put("USA", "Washinton, D.C.");
		capitals.put("England", "London");
		capitals.put("Portugal", "lisboa");
		
		System.out.println(capitals);
		
		Map<String, Integer> itens = new HashMap<String, Integer>();
		itens.put("Orange", 2);
		itens.put("Banana", 10);
		itens.put("Apple", 32);
		itens.put("Mango", 43);
		itens.put("Pear", 5);
		itens.put("Pineapple", 23);
		
		itens.put("Pear", 6);
		
		System.out.println(itens);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please type the paragraph: ");
		String paragraph = sc.nextLine();	
		String normalizedText = normalizeText(paragraph);
		
		System.out.println(normalizedText);
	
		String[] words = normalizedText.split(" ");
		
		Map<String,Integer> wordFrequencies = new HashMap<>();
		
		for(String word:words) {
			if(!word.isEmpty()) {
				wordFrequencies.put(word,wordFrequencies.getOrDefault(word, 0) + 1);
			}
		}
		
		String mostFrequentWord = null;
		Iterator<Map.Entry<String, Integer>> iterator = wordFrequencies.entrySet().iterator();
		int maxFrequency = 0;
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			if(entry.getValue() > maxFrequency) {
				maxFrequency = entry.getValue();
				mostFrequentWord = entry.getKey();
			}
			System.out.println(entry);
		}
		
		if(mostFrequentWord != null) {
			System.out.println("Most frequent word: " + mostFrequentWord + "\nwith Frequency: " + maxFrequency);
		}
		else {
			System.out.println("No valid words was found.");
		}
	}
	
	private static String normalizeText(String text) {
		StringBuilder normalized = new StringBuilder();
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if(Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
				normalized.append(Character.toLowerCase(c));
			}
		}
		return normalized.toString();
	}
	
	

}
