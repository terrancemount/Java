/*****************************************************************
Author: KC Mount
3700 Sharon Gagnon Lane #M094
Anchorage, AK 99508
(907) 252-5055
iskrm3@uaa.alaska.edu


Project Name:MountT CS203 Asmt6
DateCreated: April 8, 2005
Project purpose:  To create and populate an AVL tree from
					a text file.  Then display, then destroy
					the AVL tree and make a heap, then destroy
					the heap and to display it.


File Name:Test.java
DateCreated: April 8, 2005
File Purpose: This class contains the main method that
				runs the rest of the program. The main
				method will read in text from a text file,
				check if it is a valid word then push
				the word on the AVL tree.  Then it will
				print out the contents of the AVL tree.
				Then, it will destroy the AVL tree and
				create a heap. Then it will destroy the
				heap inorder to display the heap.
******************************************************************
************************************************************************************************
PUBLIC METHODS

Heap()  Constructor for default size

Heap(int heapSize) Constructor with non default size of heap

isEmpty()  returns boolean if size is equal to zero

ifFull() return boolean if size is eqaul to heapArray size

push(ComparableWithFrequency theElement) pushes an object on the heap with reconition of frequency

pop() returns ComparableWithFrequency object with reconition of frequency

popObject() returns ComparableWithFrequency object without reconition of frequency

findMin() returns ComparableWithFrequency object in the root of the heap

**************************************************************************************************/




public class Heap
{
	private ComparableWithFrequency[] heapArray; //the array to hold the heap
	private int size;	//used to hold the current number of unique elements in the heap

	public static final int HEAP_DEFAULT_SIZE = 100;  //default for seting up the heap
    public static final int HEAP_ROOT = 1; //used many times to increment the start position in the heapArray


	//Constructor default size
	public Heap()
	{
		this(HEAP_DEFAULT_SIZE);
	}

	//Constructor specific size of array
	public Heap(int heapSize)
	{
		//create array
		heapArray = new ComparableWithFrequency[heapSize + 1];

		//initailize the size to zero elements
		size = 0;
	}

	//this method checks if heap is empty
	public boolean isEmpty()
	{
		return size == 0;
	}

	//this method checks if the heap array is full
	public boolean isFull()
	{
		return (size == heapArray.length - 1);
	}


	//this method pushes a comparableWithFreqency element onto the heap
	public void push(ComparableWithFrequency theElement)
	{
		int elementLocation; //used to hold the location the element goes into

		if(theElement != null)
		{

			//the method will look for the element in the heap and return ether a
			//zero if it can't find it or the array location int value
			elementLocation = findElement(theElement);


			if(elementLocation > 0)
			{
				heapArray[elementLocation].incrementFrequency();

				percolateUp(elementLocation);
			}

			else if(!isFull())
			{
					size++;

					heapArray[size] = theElement;

					percolateUp(size);
			}
		}
	}

	//this will pop of the highest priority object first with reconition of frequencey
	public ComparableWithFrequency pop()
	{
		ComparableWithFrequency root;

		root = heapArray[HEAP_ROOT];

		if(!isEmpty() && heapArray[HEAP_ROOT].getFrequency() > 1)

			heapArray[HEAP_ROOT].decrementFrequency();

		else if(!isEmpty())
		{
			heapArray[HEAP_ROOT] = heapArray[size];

			size--;

			percolateDown(HEAP_ROOT);
		}
		else
			return null;

		return root;
	}

	//this will pop off the highest priority object without reconition of frequency
	public ComparableWithFrequency popObject()
	{
		ComparableWithFrequency root;

		root = heapArray[HEAP_ROOT];

		if(!isEmpty())
		{
			heapArray[HEAP_ROOT] = heapArray[size];

			size--;

			percolateDown(HEAP_ROOT);
		}
		else
			return null;

		return root;
	}


	//this method will return the highest priority object in the heap
	public ComparableWithFrequency findMin()
	{
		return heapArray[HEAP_ROOT];
	}


	//this method will percolate up the object when doing an insert
	private void percolateUp(int location)
	{
		boolean hasSwaps = true;

		ComparableWithFrequency swapObject;

		while(hasSwaps)
		{
			if(location / 2 < HEAP_ROOT)

				hasSwaps = false;

			else if(heapArray[location].compareToFrequency(heapArray[location / 2]) > 0)
			{
				swapObject = heapArray[location];

				heapArray[location] = heapArray[location / 2];

				heapArray[location / 2] = swapObject;

				location = location / 2;
			}
			else

				hasSwaps = false;
		}
	}

	//this method will percolate down a method when removing an object from a heap
	private void percolateDown(int location)
	{
		boolean hasSwaps = true;

		while(hasSwaps)
		{
			hasSwaps = false;

			if((location * 2) <= size)
			{
				if(location * 2 + 1 <= size)

					if(heapArray[location * 2].compareToFrequency(heapArray[location * 2 + 1]) < 0
						&& heapArray[location].compareToFrequency(heapArray[location * 2 + 1]) < 0)
					{
							swapInHeap(location, location * 2 + 1);

							location = location * 2 + 1;

							hasSwaps = true;
					}

				if(!hasSwaps)

					if(heapArray[location].compareToFrequency(heapArray[location * 2]) < 0)
					{
						swapInHeap(location, location * 2);

						location = location  * 2;

						hasSwaps = true;
					}
			}

		}//close the while loop

	}//close method



	//this method simply swaps the objects in the array locations
	private void swapInHeap(int locationOne, int locationTwo)
	{
		ComparableWithFrequency swapObject;

		swapObject = heapArray[locationOne];

		heapArray[locationOne] = heapArray[locationTwo];

		heapArray[locationTwo] = swapObject;
	}


	//this method will find the location of an element and return that location or
	//	it will return -1 if not found in heap
	private int findElement(ComparableWithFrequency theElement)
	{
		int count;

		count = HEAP_ROOT;

		for(count = HEAP_ROOT; count < heapArray.length; count++)
		{
			if(theElement.compareTo(heapArray[count]) == 0)

				return count;
		}
		return -1;
	}

}
