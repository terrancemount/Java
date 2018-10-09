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
//File Purpose: This interface will define a comparable object
//					to include some frequency methods
//*****************************************************
public interface ComparableWithFrequency
{
	public int compareTo(Object theObject);

	public void decrementFrequency();

	public void incrementFrequency();

	public int getFrequency();

	public int compareToFrequency(ComparableWithFrequency theObject);
}