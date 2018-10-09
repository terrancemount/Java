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


File Name: MainDriver.java
DateCreated: April 25, 2005
File Purpose: This class is the driving program.
******************************************************************/

public class PartialPath implements Comparable
{
	/************************************************************************
	 *This class holds the partial paths and total weight for the least cost
	 *	path algorithm.  totalWieght is the sum of the wieghts to get to the
	 *	last vertice in the path.  The linkedList containing the path will all
	 *	numeric representations of the vertexes.
	 *************************************************************************/
	private LinkedList path; //contains the path of vertices so far
	private double totalWieght; //contains the total wieght to get to the last vertex

	//Constructor with no vertices
	public PartialPath()
	{
		//create the LinkedList
		path = new LinkedList();

		//no path = no wieght
		totalWieght = 0;
	}

	//Constructor that creates the Linked List, creates an integer version of
	//	the number corrosponding to the vertex, and pushes the vertex num on the
	//	linked list, finally it sets the totalWieght to 0 because the path goes to
	//	only one vertex
	public PartialPath(int vertexNum)
	{
		//create an object version of vertexNum to be put in the LinkedList
		Integer vertexNumObject = new Integer(vertexNum);

		//create the linked list
		path = new LinkedList();

		//add the single vertex to the linked list
		path.addItemHead(vertexNumObject);

		//have not gone anywhere so total wieght is zero
		totalWieght = 0;
	}

	//This method will print the partial path to the screen
	public void printPath()
	{
		//call the link list toString method
		System.out.println(path.toString());
	}

	//Constructor for adding a node to the sourcePath and updating total weight
	public PartialPath(int nextVertexNum, double edgeWieght, PartialPath sourcePath) throws VertexAlreadExistsException
	{

		//create an object version of vertexNum to be put in the LinkedList
		Integer vertexNumObject = new Integer(nextVertexNum);

		//this checks if nextVertexNum is on the list already
		if(sourcePath.getPath().isOnList(vertexNumObject))

			//this is a userdefined exception class that will stop creation of a path with a cycle
			throw new VertexAlreadExistsException();

		//this sets the new object of PartialPath to have the same path LinkedList
		//	as sourcePath
		path = sourcePath.getPath();

		//add the single vertex to the linked list
		path.addItemHead(vertexNumObject);

		//this adds the edgeWieght of the added vertex and the sourcePath's totalWieght
		totalWieght = edgeWieght + sourcePath.getWieght();
	}

	//this will return the size of the partial path
	public int getSizePath()
	{
		return(path.getSize());
	}

	//this will return the numeric representation of the last vertex to be visited
	public int getLastVertex()
	{
		return((Integer)path.getHeadData()).intValue();
	}

	//This method will clone the entire PartialPath object
	public PartialPath clonePartialPath()
	{
		//create clone PartialPath object
		PartialPath clonePP = new PartialPath();

		//this clones the LinkedList and sets it for the path in the clonePartialPath
		clonePP.setPath(path.cloneList());

		//this will set the wieght for the clone;
		clonePP.setWieght(this.getWieght());

		return clonePP;
	}

	//This method will add a vertex to the path and update the totalWieght
	//	NOTE: this method will not check for cycles.
	public void addVertex(int nextVertexNum, double edgeWieght)
	{
		//create an object version of vertexNum to be put in the LinkedList
		Integer vertexNumObject = new Integer(nextVertexNum);

		//add the single vertex to the linked list
		path.addItemHead(vertexNumObject);

		//this adds the edgeWieght of the added vertex to the totalWieght of previous path
		totalWieght = totalWieght + edgeWieght;
	}

	//This method compare this PartailPath object to another object.  If the other
	//	object is not of type PartialPath or is equal to null this method will
	//	return a 1(meaning this PartialPath is greater than the otherObject)
	//	else it compares the wieghts for this object the the other one.
	//	(1 = greater than; -1 = less than;  0  = equal to)
	public int compareTo(Object otherObject)
	{

		PartialPath otherPath; //used to hold the casted otherObject to PartailPath

		//checks if other object is not an instance of PartialPath and is null
		if(otherObject == null || !(otherObject instanceof PartialPath))

			System.out.println("OtherObject is null");

		//casts the otherObject to PartialPath
		otherPath = (PartialPath)otherObject;


		//checks if greater than
		if(totalWieght > otherPath.getWieght())

			return 1;


		//checks if less than
		if(totalWieght < otherPath.getWieght())

			return -1;

		//if not greater or less than then it must be eqaul (return 0)
		return 0;
	}

	//this gets the wieght
	public double getWieght()
	{
		return totalWieght;
	}

	//this sets the wieght
	public void setWieght(double theWieght)
	{
		totalWieght = theWieght;
	}

	//this gets path
	public LinkedList getPath()
	{
		return path;
	}
	//this sets the private LinkedList path var
	public void setPath(LinkedList thePath)
	{
		path = thePath;
	}


}
