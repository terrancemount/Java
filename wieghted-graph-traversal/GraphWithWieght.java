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
File Purpose: This class contols all the Graph's functions.
******************************************************************/


public class GraphWithWieght
{
	private int numOfVertices; //used to store the number of vertices in the graph
	private double[][] adjacencyMatrix; //used to store all the edge wieghts for the graph
	PartialPath newPath; //used when creating a new Partial Path object (used many times)
	Heap myHeap;  //used to hold the heap for the findLeastWieghtTour
	LinkedList completePath; //this is the complete path that is stored when calling the findLeastWieghtTour
	double completeWieght; //this is the complete path's wieght that is stored when calling the findLeastWieghtTour


	public static final int DEFAULT_SIZE = 10; //this is used as a default size for the number of vertexes in the graph

	//Constructor that uses the default size
	public GraphWithWieght()
	{
		this(DEFAULT_SIZE);
    }

	//Constuctor that set the numOfVertices and creates the adjacencyMatrix
	public GraphWithWieght(int theSize)
	{
		//this sets the private var for the number of vertices
		numOfVertices = theSize;

		//this makes the array to hold the edge weights
		adjacencyMatrix = new double[numOfVertices][numOfVertices];

		//this initializes the adjacentcy Matrix to all negative numbers
		initailizeAdjacencyMatrix();
	}

	//this method will add one edge wieght at a time to the adjacency matrix
	public void addEdgeWieght(int sourceVertex, int destinationVertex, double theWieght)
	{
		adjacencyMatrix[sourceVertex][destinationVertex] = theWieght;
	}

	//This is the beast of a method that finds the least cost tour of all the points and back again
	public void findLeastWieghtTour(int startPosition)
	{
		PartialPath currentPath; //this is holds the current PartialPath the algorithym is looking at
		int lastVertex;  //this is derived from the first entry in the PartialPath linked list of the path

		//This will create a heap object each time the findLeastWieghtTour is called to make
		//	sure that there is a blank heap.
		myHeap = new Heap(numOfVertices * numOfVertices *  numOfVertices * numOfVertices * numOfVertices);

		//this creates a partial path with the first vertex
		newPath = new PartialPath(startPosition);

		//this sets the currentPath to the "first" partial path object (starting position)
		currentPath = newPath;

		//This will loop until the size of the partial path is greater than the numOfVertices
		while(currentPath.getSizePath() <= numOfVertices)
		{

			//this gets the last vertex visited by algorithym
			lastVertex = currentPath.getLastVertex();

			//this checks if algorithym has looked visited ever vertex once (add edge back to start only)
			if(currentPath.getSizePath() == numOfVertices)
			{
				//this adds on the return edge to the startPosition after visiting ever other vertex
				currentPath.addVertex(startPosition, adjacencyMatrix[lastVertex][startPosition]);

				//this pushes one complete path back on the heap so when it is pop off again
				//	then it will be the lowest wieght Path.(Might be more than one complete path)
				myHeap.push(currentPath);
			}
			else //the number of vertices in the partial path is less than the total number of vertices
			{
				//this will create a new partial path object for each possible destination from the last vertex.
				for(int destination = 0; destination < numOfVertices; destination++)
				{
					//this checks if the edge wiegth is greater than zero and the destination vertex is not closed
					if(adjacencyMatrix[lastVertex][destination] > 0)
					{
						//this try will catch an already exists exception when the algorithym try to add a
						//	vertex that has already be visited in the list
						try
						{
							//this will create a new PartialPath object with the destination vertex, the edge wieght and the
							//	clone of the currentPath.
							newPath = new PartialPath(destination, adjacencyMatrix[lastVertex][destination], currentPath.clonePartialPath());

							//push the new object back on the heap
							myHeap.push(newPath);
						}
						catch(VertexAlreadExistsException e)
						{
							//do nothing, just stops the creation of the new path object
							//	if the destination is already in the path
						}
					}
				}
			}

			//this will look at the lowest wieght Path always
			//	and sets up for next run throw the loop
			currentPath = (PartialPath)myHeap.pop();

		}//end of adding vertex loop

		//this is a complete lowest wieght Path that goes from startPosition
		//	to every other vertex and back to startPosition
		completePath = currentPath.getPath();


		//This sets the path wieght to the class level var to be displayed
		completeWieght = currentPath.getWieght();
	}

	//This method will calculate the shortes path from on point to another
	//	Note: this is not a completely tested and running method. ie needs some more work =)
	public void goToVertex(int start, int end)
	{
		PartialPath currentPath; //this is holds the current PartialPath the algorithym is looking at
		int lastVertex;  //this is derived from the first entry in the PartialPath linked list of the path

		//This will create a heap object each time the findLeastWieghtTour is called to make
		//	sure that there is a blank heap.
		myHeap = new Heap(numOfVertices * numOfVertices * numOfVertices);

		//this creates a partial path with the first vertex
		newPath = new PartialPath(start);

		//this sets the currentPath to the "first" partial path object (starting position)
		currentPath = newPath;

		//This will loop until the size of the partial path is greater than the numOfVertices
		while(currentPath != null && currentPath.getLastVertex() != end )
		{

			//this gets the last vertex visited by algorithym
			lastVertex = currentPath.getLastVertex();

			//this will create a new partial path object for each possible destination from the last vertex.
			for(int destination = 0; destination < numOfVertices; destination++)

				//this checks if the edge wiegth is greater than zero and the destination vertex is not closed
				if(adjacencyMatrix[lastVertex][destination] > 0)

					//this try will catch an already exists exception when the algorithym try to add a
					//	vertex that has already be visited in the list
					try
					{
						//this will create a new PartialPath object with the destination vertex, the edge wieght and the
						//	clone of the currentPath.
						newPath = new PartialPath(destination, adjacencyMatrix[lastVertex][destination], currentPath.clonePartialPath());

						//newPath.printPath(newPath);
						myHeap.push(newPath);
					}
					catch(VertexAlreadExistsException e)
					{
						//do nothing, just stops the creation of the new path object
						//	if the destination is already in the path
					}

			//this will look at the lowest wieght Path always
			//	and sets up for next run throw the loop
			currentPath = (PartialPath)myHeap.pop();

		}//end of adding vertex loop

		//This check if there was a path Found(currentPath not null)
		if(currentPath == null)
		{
			completePath = null;
		}
		else
		{
			//this is a complete lowest wieght Path that goes from startPosition
			//	to every other vertex and back to startPosition
			completePath = currentPath.getPath();


			//This sets the path wieght to the class level var to be displayed
			completeWieght = currentPath.getWieght();
		}
	}


	//This method will return the partial path as a string
	public String toString()
	{
		return completePath.toString();
	}

	//This method will return the complete partial path as a double
	public double getWieght()
	{
		return completeWieght;
	}

	//This method will check if there is a valid path stored in completePath
	public boolean isValidPath()
	{
		return(completePath != null);
	}

	//This method will print out the adjacencyMatrix
	public void printAdjacencyMatrix()
	{
		int i, k; //used for counters

		System.out.print("\t");

		for(i = 0; i < numOfVertices; i++)

			System.out.print(i + "\t");

		System.out.println();

		for(i = 0; i < numOfVertices; i++)
		{
			System.out.print(i + "\t");

			for(k = 0; k < numOfVertices; k++)

				System.out.print(adjacencyMatrix[i][k] + "\t");

			System.out.println();
		}
	}

	//This method will set all the values in the list to a negative number
	private void initailizeAdjacencyMatrix()
	{
		//used in the for loops as counters
		int i, k;

		//this loop will set to negative the wieght of the rows in  the matix
		for(i = 0; i < numOfVertices; i++)
		{
			//this loop will set to negative the wieght of the colums in the matrix
			for(k = 0; k < numOfVertices; k++)
			{
				//used negative wieght as non valid wieght
				adjacencyMatrix[i][k] = -1.0;
			}
		}
	}


}
