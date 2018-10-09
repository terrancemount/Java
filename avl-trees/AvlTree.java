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
//File Name:AvlTree.java
//DateCreated: April 8, 2005
//File Purpose: The purpose of this class to maintain an
//				AVL tree with all the associated methods.
//*****************************************************




public class AvlTree
{
	private AvlNode root; // pointer to the begining of the AvlTree
	private int size;
	private String wordList;

	//Constructor
	public AvlTree()
	{
		root = null;
	}

	//this method will call the recursive insert method with root to start out
	public void insert(ComparableWithFrequency theElement)
	{
		root = insert(theElement, root);
	}

	//this method will call the recursive insert method with root to start out
	public void remove(ComparableWithFrequency theElement)
	{
		root = remove(theElement, root);
	}

	public String toString()
	{
		wordList = "";

		toString(root);

		return wordList;
	}

	//this method will call the
	public ComparableWithFrequency removeRootElement()
	{
		ComparableWithFrequency theElement;

		theElement = root.element;

		remove(theElement);

		return theElement;
	}

	public ComparableWithFrequency findMin()
	{
		return findMin(root).element;
	}

	public ComparableWithFrequency findMax()
	{
		return findMax(root).element;
	}

	public ComparableWithFrequency find(ComparableWithFrequency theElement)
	{
		return find(theElement, root).element;
	}

	public void makeEmpty()
	{
		root = null;
	}

	public boolean isEmpty()
	{
		return root == null;
	}

	public int getSize()
	{
		return size;
	}

	private AvlNode insert(ComparableWithFrequency theElement, AvlNode node)
	{
		//************************************************************
		//This method inserts an element into the Avl tree.  There are
		//	four main selections this method checks for:  if the node is
		//	null - with a new node is made with the element.  If theElement
		//	is greater than the node.element - then insert on the left.  If
		//	theElement is greater than the node.element then insert on the
		//	right..  If theElement is equal to the node.element then
		// 	this method will increment to the frequency counter in the
		//	ComparableWithFrequency object.
		//**************************************************************

		//this checks if the insert is at the end of search and needs
		//	to make a new node.
		if(node == null)
		{
			//create the new node;
			node = new AvlNode(theElement, null, null);

			size++;
		}

		//checks when theElement is less than the node.element
		else if(theElement.compareTo(node.element) < 0)
		{
			//recursive call to insert theElement on the left child
			node.left = insert(theElement, node.left);

			//checks if the left child is higher than the right
			if(height(node.left) - height(node.right) > 1)
			{
				//if imbalanced, this will check  if theElement was
				//	less than the leftchild's element (SINGLE ROTATION)
				if(theElement.compareTo(node.left.element) < 0)

					//this method will rotate the left child once
					node = rotateWithLeftChild(node);

					//else it was less than (DOUBLE ROTATION)
				else
					//this method will rotate the left child twice
					node = doubleWithLeftChild(node);
			}
		}

			//check when theElement is greater then the node.element
		else if(theElement.compareTo(node.element) > 0)
		{
			//recursive call to insert theElement on the right child
			node.right = insert(theElement, node.right);

			//checks if the right child is higher than the left
			if(height(node.right) - height(node.left) > 1)
			{
				//if imbalanced, this will check  if theElement was
				//	less than the rightchild's element (SINGLE ROTATION)
				if(theElement.compareTo(node.right.element) > 0)

					//this method will rotate the right child once
					node = rotateWithRightChild(node);

					//else it was less than (DOUBLE ROTATION)
				else

					//this method will rotate the right child twice
					node = doubleWithRightChild(node);
			}
		}
			//when the node.element is equal to theElement
		else

			//this increments the frequency of the element (duplication)
			node.element.incrementFrequency();

		//sets the node height
		node.height = max(height(node.left), height(node.right)) +1;

		return node;
	}

	//This method will remove an object
	private AvlNode remove(ComparableWithFrequency theElement, AvlNode node)
	{
		if(node == null)

			return node;

		if(theElement.compareTo(node.element)<0)

			node.left = remove(theElement, node.left);

		else if (theElement.compareTo(node.element)>0)

			node.right = remove(theElement, node.right);

		else if(node.element.getFrequency() > 1)

			node.element.decrementFrequency();

		else
		{
			size--;

			if(node.left != null && node.right !=  null)
			{

				node.element = findMax(node.left).element;

				node.left = remove(node.element, node.left);
			}
			else
			{
				if (node.left != null)

					node = node.left;

				else

					node =  node.right;
			}
		}
		if(node != null)
		{
			if((height(node.right) - height(node.left)) > 1)
			{

				if(height(node.right.left) > height(node.right.right))

					node = doubleWithRightChild(node);

				else

					node = rotateWithRightChild(node);
			}

			if ((height(node.left) - height(node.right)) > 1)
			{
				if(height(node.left.right) > height(node.left.left))

					node = doubleWithLeftChild(node);

				else

					node = rotateWithLeftChild(node);
			}

			node.height = max(height(node.left), height(node.right)) + 1;
		}
		return node;
	}

	private void toString(AvlNode node)
	{
		if(node.left != null)

			toString(node.left);

		wordList = wordList + ((Words)(node.element)).getWord() + "\n";

		if(node.right != null)

			toString(node.right);
	}

	private AvlNode findMin(AvlNode node)
	{
		if(node.left != null)

			return findMin(node.left);

		return node;
	}

	private AvlNode findMax(AvlNode node)
	{
		if(node.right != null)

			return findMax(node.right);

		return node;
	}

	private AvlNode find(ComparableWithFrequency theElement, AvlNode node)
	{
		if(node == null)

			return node;

		if(theElement.compareTo(node.element) < 0)

			return find(theElement, node.left);

		if(theElement.compareTo(node.element) > 0)

			return find(theElement, node.right);

		return node;
	}

	private static int height(AvlNode node)
	{
		//*******************************************
		//This method will return the height of the
		//	parameter noded
		//*******************************************

		if(node == null)

			return 0;

		return node.height;
	}

	private static int max(int leftHeight, int rightHeight)
	{
		//****************************************
		//This method check the height of the two
		//	integers.
		//**************************************

		//if left is bigger.
		if(leftHeight > rightHeight)

			return leftHeight;

		//if equal or right is bigger
		return rightHeight;
	}

	private static AvlNode rotateWithLeftChild(AvlNode nodeOne)
	{
		//*****************************************************
		//This method will rotate left the AvlNode and return
		//	the rotated tree (SINGLE ROTATION)
		//*****************************************************

		AvlNode nodeTwo;//used to rotate nodes

		//sets nodeTwo to nodeOnes left child
		nodeTwo = nodeOne.left;

		//this switches the left child of node one to the right child of node two
		nodeOne.left = nodeTwo.right;

		//this sets nodeOne to the right child of nodeTwo.
		nodeTwo.right = nodeOne;

		//this sets the hieght of node one my comparing the heights of its childern
		nodeOne.height = max(height(nodeOne.left), height(nodeOne.right)) +1;

		//this sets the hieght of node one my comparing the heights of its childern
		nodeTwo.height = max(height(nodeTwo.left), nodeOne.height) +1;

		return nodeTwo;
	}

	private static AvlNode rotateWithRightChild(AvlNode nodeOne)
	{
		//*****************************************************
		//This method will rotate right the AvlNode and return
		//	the rotated tree (SINGLE ROTATION)
		//*****************************************************

		AvlNode nodeTwo;//used to rotate nodes

		//sets nodeTwo to nodeOnes right child
		nodeTwo = nodeOne.right;

		//this switches the right child of node one to the left child of node two
		nodeOne.right = nodeTwo.left;

		//this sets nodeOne to the left child of nodeTwo.
		nodeTwo.left = nodeOne;

		//this sets the hieght of node one my comparing the heights of its childern
		nodeOne.height = max(height(nodeOne.left), height(nodeOne.right)) +1;

		//this sets the hieght of node one my comparing the heights of its childern
		nodeTwo.height = max(height(nodeTwo.right), nodeOne.height) +1;

		return nodeTwo;
	}

	private static AvlNode doubleWithLeftChild(AvlNode nodeOne)
	{
		//********************************************************
		//This method will do a rotate right first then rotate left
		//	(DOUBLE ROTATION)
		//*********************************************************

		//this rotates the left child to the right
		nodeOne.left = rotateWithRightChild(nodeOne.left);

		//this rotate the entire node left
		return rotateWithLeftChild(nodeOne);
	}

	private static AvlNode doubleWithRightChild(AvlNode nodeOne)
	{
		//********************************************************
		//This method will do a rotate left first then rotate right
		//	(DOUBLE ROTATION)
		//*********************************************************

		//this rotates the right child to the left
		nodeOne.right = rotateWithLeftChild(nodeOne.right);

		//this rotate the entire node right
		return rotateWithRightChild(nodeOne);
	}

	protected class AvlNode
	{
		//*******************************************************
		//This class is used to create new nodes for the tree.
		//	It must be inside the class for the AvlTree.
		//*********************************************************
		protected ComparableWithFrequency element;  //holds the data for the tree
		protected AvlNode right; //pointer to right child
		protected AvlNode left; //pointer to left child
		protected int height; //hold number of levels from leaf node


		//Constructor that sets right and left to null
		public AvlNode(ComparableWithFrequency theElement)
		{
			this(theElement, null, null);
		}

		//Constructor that set element, right and left.  Height to 0
		public AvlNode(ComparableWithFrequency theElement, AvlNode rightChild, AvlNode leftChild)
		{
			element = theElement;

			right = rightChild;

			left = leftChild;

			height = 0;
		}
	}

}
