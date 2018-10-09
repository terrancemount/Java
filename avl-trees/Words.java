//***************************************************
//Author: KC Mount
//3700 Sharon Gagnon Lane #M094
//Anchorage, AK 99508
//(907) 252-5055
//iskrm3@uaa.alaska.edu
//
//
//Project Name:MountT CS203 Asmt6
//DateCreated: April 8, 2005
//Project purpose:  To create and populate an AVL tree from
//					a text file.  Then display, then destroy
//					the AVL tree and make a heap, then destroy
//					the heap and to display it.
//
//
//File Name:Test.java
//DateCreated: April 8, 2005
//File Purpose: This class contains the main method that
//				runs the rest of the program. The main
//				method will read in text from a text file,
//				check if it is a valid word then push
//				the word on the AVL tree.  Then it will
//				print out the contents of the AVL tree.
//				Then, it will destroy the AVL tree and
//				create a heap. Then it will destroy the
//				heap inorder to display the heap.
//*****************************************************



public class Words implements Comparable, ComparableWithFrequency
{
	private String word; //used to hold the word this class is representing
	private int frequency; //used to hold how often the word occuars

	//constructor that set word to empty and frequency to 0
	public Words()
	{
		this("", 0);
	}

	//constructor that sets frequencey to 1
	public Words(String newWord)
	{
		this(newWord, 1);
	}

	//constructor that set both the frequency and word
	public Words(String newWord, int theFreq)
	{
		word = newWord;

		frequency = theFreq;
	}

	public int compareTo(Object otherWord)
	{
		//******************************************
		//This method is used to implement the
		//	Comparable interface.  This class
		//	will return the result from the
		//	string compareTo method for the
		//	word in this class and the otherWord.
		//	If the otherWord is invalid or not of
		//	the Word class then this method will
		//	return 1 (greater than).
		//	NOTE: this compares alphabetically
		//*********************************************

		Words compareWord; //used to hold the casted otherWord to Words class

		//checks that other word is valid to compare
		if(otherWord != null && otherWord instanceof Words)
		{
			//casts otherWord to Words class
			compareWord = (Words)otherWord;

			//calls the compareTo method from string
			return word.compareTo(compareWord.getWord());
		}

		//if otherWord is invald then return greater than
		return 1;
	}

	public int compareToFrequency(ComparableWithFrequency otherWord)
	{
		//******************************************
		//This method compares the two frequencies
		//	of this object and otherWord. If the
		//	freqencies differ then this method will
		//	return the correct interger corrosponding
		//	to a compareTo method.  If they are the
		//	same this method will call the compareTo
		//	for this class.
		//******************************************


		//checks that other word is valid to compare
		if(otherWord == null || !(otherWord instanceof Words))

			return 1;

		//if greater than return positive interger
		if(frequency > otherWord.getFrequency())

			return 1;

		//if less than return negative integer
		if(frequency < otherWord.getFrequency())

			return -1;

		//if equal compare the words alphabetically
		return otherWord.compareTo(this);
	}

	//set the word
	public void setWord(String newWord)
	{
		word = newWord;
	}

	//get the word
	public String getWord()
	{
		return word;
	}

	//increment frequency counter
	public void incrementFrequency()
	{
		frequency++;
	}

	//decrement frequency counter if greater than one
	// if less than one the object should not exist
	public void decrementFrequency()
	{
		if(frequency > 1)

			frequency--;
	}

	//get freqency counter
	public int getFrequency()
	{
		return frequency;
	}
}