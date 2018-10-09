/*****************************************************************
Author: KC Mount
3700 Sharon Gagnon Lane #M094
Anchorage, AK 99508
(907) 252-5055
iskrm3@uaa.alaska.edu


Project Name:MountT CS203 Asmt 7
DateCreated: April 25, 2005
Project purpose: To create a graph with random edge wieghts, then
					find the least cost path to all the vertices and
					back to starting vertex.


File Name: GraphWithWieght.java
DateCreated: April 25, 2005
File Purpose: This class is a basic definition for a Linked List
******************************************************************/

public class LinkedList
{
	private ListNode head; //used to point to the head node of the linked list
	private ListNode next;  //used as a referance to the node the algorithm is looking at (used many times)
	private int size;  //number of nodes in the linked list

	//Constuctor - set up all local vars
	public LinkedList()
	{
		head = null;
		next = null;
		size = 0;

	}

	//This method will create a new node at the head of the list with theObject
	public void addItemHead(Comparable theObject)
	{
		//this will create a new object and set it to head with the link to the old head
		head = new ListNode(theObject, head);

		//increment the number of elements in the list
		size++;
	}

	public void addItemEnd(Comparable theObject)
	{

		next = head;

		if(next == null)

			head = new ListNode(theObject, null);

		else
		{
			while(next.link != null)

				next = next.link;

			next.link = new ListNode(theObject, null);
		}
		size++;
	}



	public boolean isOnList(Comparable theData)
	{
		next = head;

		while(next != null)
		{
			if(next.data.compareTo(theData) == 0)

				return true;

			next = next.link;
		}
		return false;
	}

	public String toString()
	{
		next = head;

		String printString = "";

		while(next != null)
		{
			printString = ((Integer)next.data).toString() + "  " + printString;

			next = next.link;
		}

		return printString;
	}

	public LinkedList cloneList()
	{
		LinkedList clone = new LinkedList();

		next = head;

		while(next != null)
		{
			clone.addItemEnd(next.data);

			next = next.link;
		}

		return clone;
	}


	public boolean isEmpty()
	{
		return(size == 0);
	}

	public int getSize()
	{
		return size;
	}
	public Comparable getHeadData()
	{
		return head.data;
	}

	private ListNode getHeadLink()
	{
		return head;
	}


	private class ListNode
    {
        private Comparable data;
        private ListNode link;

		//dump constructor
        public ListNode( )
        {
            link = null;
            data = null;
        }

		//smart constuctor(set the data and link at same time)
        public ListNode(Comparable newData, ListNode linkValue)
        {
            data = newData;
            link = linkValue;
        }
    }

}