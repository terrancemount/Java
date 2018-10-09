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

import java.io.*;
import java.util.*;

public class TestMain
{
	public static void main(String[] args)
	{
		AvlTree myTree = new AvlTree();  //the creation of a new AVL tree object
		Heap myHeap; //create the heap pointer that will be later created and sized for the Avl tree
		Words myWord;	//used alot to hold a Words object throught the program

		String tempString = ""; //used to hold the cleaned parsed string to place in the AVL tree
		BufferedReader myReader;	//use to open a file
		StringTokenizer dataFinder;  //used to parse the strings
		String line;	//used to hold the each line from the file
		String junk; //used to stop program from running until user hits enter
		String toPrintHeap; //string used to print the results from a traversal of the heap

		//this try block is to make sure there is no trouble with opening and useing the txt file
		try
		{
			//open the file
			myReader = new BufferedReader(new FileReader("default.txt"));

			//read first line in to test in the while loop
			line = myReader.readLine();

			//while loop to check for the end of file
			while(line != null)
			{
				//create a new StringTokenizer class with specific deliminators
				dataFinder = new StringTokenizer(line, " -.,\t\n()\\/\"");

				//this will loop will check through the whole line for words
				while(dataFinder.hasMoreTokens())
				{
					//get the word from the StringTokenizer class
					tempString = dataFinder.nextToken();

					//clean of whitespace
					tempString = tempString.trim();

					//convert string to upperCase
					tempString = tempString.toUpperCase();


					if(isWord(tempString))
					{
						myWord = new Words(tempString);

						myTree.insert(myWord);


					}
				}

				line = myReader.readLine();
			}

			myReader.close();



		}
		catch(FileNotFoundException e)
		{
			System.out.println("File is not found");
		}
		catch(IOException E)
		{
			System.out.println("Error reading a file");
		}


		System.out.println("This program will read an imput file named default.txt");

		//This prompts user to continue
		System.out.println("\nPress Enter to Contiune");

		//read in the junk "return" to continue
		junk = SavitchIn.readLine();


		//this prints out the header for the infix traversal of the Avl Tree
		System.out.println("****************************************************************");
		System.out.println("*                Alphabetical Ordering of AVL Tree             *");
		System.out.println("****************************************************************");




		//This will print out the Avl Tree in order;
		System.out.println(myTree.toString());

		//This prompts user to continue
		System.out.println("Press Enter to Contiune");

		//read in the junk "return" to continue
		junk = SavitchIn.readLine();


		//this creates the heap with the size of the AVL node
		myHeap = new Heap(myTree.getSize());

		//this breaks down the AVL and creates a string to hold the words
		while(!myTree.isEmpty())
		{
			myWord = (Words)myTree.removeRootElement();

			tempString += myWord.getWord() + " ";
		}

		//this prepares the string from the AVL tree to be parsed in the heap
		dataFinder = new StringTokenizer(tempString);

		//this will push the words on the heap
		while(dataFinder.hasMoreTokens())
		{
			myWord = new Words(dataFinder.nextToken());

			myHeap.push(myWord);

		}


		//reset the string that holds the heap remover method output
		toPrintHeap = "";

		//this will remove the whole word objects and store the freqency plus the word in a string
		while(!myHeap.isEmpty())
		{
			myWord = (Words)myHeap.popObject();

			toPrintHeap += myWord.getFrequency() + " " + myWord.getWord() + "\n";
		}





		//this prints out the header for the remove object from head of heap method in heap class
		System.out.println("****************************************************************");
		System.out.println("*             Frequency Ordering of the Heap                   *");
		System.out.println("*             (higest frequency words first)                   *");
		System.out.println("****************************************************************");


		//This will print out the Avl Tree in order;
		System.out.println(toPrintHeap);

		//This prompts user to Exit
		System.out.println("Press Enter to Exit");

		//read in the junk "return" to continue
		junk = SavitchIn.readLine();


	}

	//this mehtod checks if the string is a upper case word
	public static boolean isWord(String word)
	{
		int count;
		char testChar;

		for(count = 0; count < word.length(); count++)
		{
			testChar = word.charAt(count);

			if(!(testChar >= 'A' && testChar <= 'Z'))

				return false;
		}
		return true;
	}




}







