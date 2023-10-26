
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Charles Chang
 *	@since	October 19, 2023
 */
import java.util.*;
 
public class WordUtils
{
	private String[] words = new String[90934];		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	/* Constructor */
	public WordUtils() { }
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () {
		Scanner input = FileUtils.openToRead(WORD_FILE);
		int a = 0;
		while (input.hasNextLine()) {
			words[a] = input.nextLine();
			a++;
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{
		EveryWord ew = new EveryWord();
		/* Array with matching words */
		String[] matches = new String[1000];
		/* What slot in matches[] to use */
		int outputPos = 0;
		for(int i = 0; i < words.length; i++) {
			/* If the word matches the letters, add that word to the next
			 * open slot in matches */
			if (ew.isWordMatch(words[i], letters)) {
				matches[outputPos] = words[i];
				outputPos++;
			}
		}
		/* How long is the matches array excluding empty spaces */
		int matchLength = 0;
		for (int i = 0; matches[i] != null; i++)
			matchLength ++;
		/* New array output that is same as matches[], but there are no 
		 * empty spaces */
		String[] output = new String[matchLength];
		for (int i = 0; i < matchLength; i++) {
			output[i] = matches[i];
		}
		return output;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) {
		for (int i = 0; i < wordList.length; i++) {
			System.out.printf("%-15s", wordList[i]);
		}
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		int[] scores = new int[wordList.length];
		for (int i = 0; i < wordList.length; i++) {
			String word = wordList[i];
			for (int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				//scores[i] += scoreTable[(int)(c - 97)];
				
			}
		}
		return "";
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		return 0;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		loadWords();
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}
