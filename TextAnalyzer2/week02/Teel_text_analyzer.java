//Author name: Charles Teel Jr.
//Date: 5/20/2020
//Program Name: Teel_text_analyzer
//Purpose reads file submitted by user and prints out most frequently used words and Top 20 words.

package week02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Teel_text_analyzer {

	public static void main(String[] args) throws FileNotFoundException {

		// Scanner Object to be used for the user file path input
		Scanner keyboard = new Scanner(System.in);

		// Asks the user to enter the full path of their file
		System.out.print("Please enter the name of the file (Macbethplay.txt) : ");

		// Stores the user input file path as a string
		String filename = keyboard.nextLine();

		// Creates a File object for the file path, and scans it
		Scanner inputFile = new Scanner(new File(filename));

		
		readText(filename);

	}

	public static void readText(String filename) throws FileNotFoundException {
		// create a hashmap and a scanner for the file
		HashMap<String, Integer> count = new HashMap<String, Integer>();

		Scanner input = new Scanner(new File(filename));

		// continue to read the file until lines run out
		while (input.hasNextLine()) {
			// get the line
			String line = input.nextLine();

			String[] words = line.split("[\\s.;,?:!()\"]+");
			for (String word : words) {
				
				word = word.trim();
				word = word.toLowerCase();
				
				if (word.length() > 0) {
					if (count.containsKey(word)) {
						count.put(word, count.get(word) + 1);
					} else {
						count.put(word, (int) 1L);
					}
				}

			}

		}

		// Sorting and printing out top 20 most used.
		Set<Map.Entry<String, Integer>> set = count.entrySet(); // retrieving the map contents
		ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set); // make an 
																											// array
																											// list
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>()
				
		// sorting the array list
		{
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) // comparator function for
																							// sorting
			{
				return (b.getValue()).compareTo(a.getValue()); // for descending order
				// return (a.getValue()).compareTo( b.getValue() ); // for ascending order
				
			}
		});
		
		//int x = numberofWords;
		
			System.out.println("All Word Frequencies from the file:");
			System.out.println(sortedList);
			System.out.println("");
			System.out.println("Most frequently used words:");
			System.out.println(sortedList.subList(0, 20));
		
	}
	
		
	}

