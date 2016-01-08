/**
Namho An
04/06/2015
COSI 12b
PA-Extra 
*/

import java.util.*;


public class Anagrams {
   //Set the constructor
   private Set<String> dictionary; 
   //constructs an Anagrams object 
   public Anagrams(Set<String> dictionary) {
      if (dictionary == null) {
         throw new IllegalArgumentException("dictionary: " + dictionary);
      } else {
         this.dictionary = new TreeSet<String>(dictionary);
      }
   }
   
   // with the given phrase, returns a set of string 
   public Set<String> getWords(String phrase) {
      if (phrase == null) {
         throw new IllegalArgumentException("phrase: " + phrase); 
      } else {
         LetterInventory letters = new LetterInventory(phrase);
         Set<String> chosen = new TreeSet<String>();
         for (String word: dictionary) {
            if (letters.contains(word)) {
               chosen.add(word);
            }
         }
         return chosen;
      }
   }
   
   // print all of the anagrams of the given phrase
   public void print(String phrase) {
      if (phrase == null) {
         throw new IllegalArgumentException("phrase :" + phrase);
      } else {
         print(getWords(phrase).size(), getWords(phrase), new LetterInventory(phrase), new Stack<String>());
      }//max word is equal to the number of words in the set of words
   }

   
   // print all of the possible anagrams of the given phrase 
   public void print(String phrase, int max) {
      if (phrase == null) {
         throw new IllegalArgumentException("phrase: " + phrase);
      } else if (max < 0) { 
         throw new IllegalArgumentException("max: " + max);
      } else {
         if (max == 0) { // total max is equal to the maximum total words to choose from  
            max = getWords(phrase).size();
         }
         print(max, getWords(phrase), new LetterInventory(phrase), new Stack<String>()); 
      }
   }  
   
   // print the result of anagrams of up to max words
   private void print(int max, Set<String> choices, LetterInventory letter, Stack<String> chosen) {
      if (letter.isEmpty()) {
         System.out.println(chosen);
      }
      else if (chosen.size() < max){
         for (String choice : choices) {
            if (letter.contains(choice)) {//chooses words from passed in choices
               LetterInventory left = new LetterInventory(letter);//track the letters to be used
               left.subtract(choice);
               //anagrams are stored and then passed in choices Stack
               chosen.push(choice);
               print(max, choices, left, chosen);
               chosen.pop();
            }
         }
      }
   }
}